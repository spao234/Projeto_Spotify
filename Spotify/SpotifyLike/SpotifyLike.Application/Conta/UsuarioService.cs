using SpotifyLike.Domain.Conta;
using SpotifyLike.Domain.Streaming;
using SpotifyLike.Domain.Transacao;
using SpotifyLike.Repository.Conta;
using SpotifyLike.Repository.Streaming;
using SpotifyLike.Repository.Transacao;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpotifyLike.Application.Conta
{
    public class UsuarioService
    {
        private IUsuarioRepository usuarioRepository;
        private IPlanoRepository planoRepository;
        private IBandaRepository bandaRepository;
        private IAzureServiceBusService azureServiceBusService;

        public UsuarioService(IUsuarioRepository usuarioRepository, IPlanoRepository planoRepository, IBandaRepository bandaRepository, IAzureServiceBusService azureServiceBusService)
        {
            this.usuarioRepository = usuarioRepository;
            this.planoRepository = planoRepository;
            this.bandaRepository = bandaRepository;
            this.azureServiceBusService = azureServiceBusService;
        }

        public Usuario CriarConta(String nome, Guid planoId, Cartao cartao)
        {
            Plano plano = this.planoRepository.GetPlanoById(planoId);

            if (plano == null)
                throw new Exception("Plano não encontrado");

            Usuario usuario = new Usuario();
            usuario.Criar(nome, plano, cartao);
            

            //Salva o usuário na base de dados
            this.usuarioRepository.Save(usuario);

            var notificacao = new Notificacao();
            notificacao.IdUsuario = usuario.Id;
            notificacao.Message = $"Seja bem vindo ao Spotify Like. Debitamos o valor de R$ {plano.Valor.ToString("N2")} no seu cartão";

            azureServiceBusService.SendMessage(notificacao).Wait();

            return usuario;

        }

        public Usuario ObterUsuario(Guid id)
        {
            var usuario = this.usuarioRepository.GetUsuario(id);
            return usuario;
        }

        public void FavoritarMusica(Guid id, Guid idMusica)
        {
            

            var usuario = this.usuarioRepository.GetUsuario(id);
            if (usuario == null) throw new Exception("Não encontrei o usuario");

            var musica = VerificarMusica(idMusica);

            usuario.FavoritarMusica(musica);

            this.usuarioRepository.Update(usuario);

        }
        

        public void DesfavoritarMusica(Guid id, Guid idMusica)
        {
            var usuario = this.usuarioRepository.GetUsuario(id);
            if (usuario == null) throw new Exception("Não encontrei o usuario");

            var musica = VerificarMusica(idMusica);

            usuario.DesfavoritarMusica(musica);

            this.usuarioRepository.Update(usuario);
        }

        private Musica VerificarMusica(Guid idMusica)
        {

            var musica = this.bandaRepository.GetMusica(idMusica);

            if (musica == null) throw new Exception("Não encontrei a musica a ser favoritada");

            return musica;
        }
    }
}
