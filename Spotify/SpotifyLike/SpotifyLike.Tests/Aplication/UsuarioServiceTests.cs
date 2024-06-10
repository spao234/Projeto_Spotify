using Moq;
using SpotifyLike.Application.Conta;
using SpotifyLike.Domain.Conta;
using SpotifyLike.Domain.Transacao;
using SpotifyLike.Repository.Conta;
using SpotifyLike.Repository.Streaming;
using SpotifyLike.Repository.Transacao;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace SpotifyLike.Tests.Aplication
{
    
    public class UsuarioServiceTests
    {
        [Fact]
        public void DeveCriarUmUsuarioComSucesso()
        {
            Mock<IPlanoRepository> mockPlano = new Mock<IPlanoRepository>();
            Mock<IUsuarioRepository> mockUsuarioRepository = new Mock<IUsuarioRepository>();
            Mock<IBandaRepository> mockBandaRepository = new Mock<IBandaRepository>();
            Mock<IAzureServiceBusService> mockAzureServiceBus= new Mock<IAzureServiceBusService>();

            mockPlano.Setup(x => x.GetPlanoById(It.IsAny<Guid>())).Returns(new SpotifyLike.Domain.Transacao.Plano()
            {
                Nome = "Plano Dummy",
                Valor = 29M
            });

            mockUsuarioRepository.Setup(x => x.Save(It.IsAny<Usuario>()));
            mockAzureServiceBus.Setup(x => x.SendMessage(It.IsAny<Notificacao>())).Returns(Task.CompletedTask);

            UsuarioService usuarioService = new UsuarioService(mockUsuarioRepository.Object, mockPlano.Object, mockBandaRepository.Object, mockAzureServiceBus.Object);

            Cartao cartao = new Cartao();
            cartao.Ativo = true;
            cartao.Numero = "564654654";
            cartao.Limite = 1000;

            var usuario = usuarioService.CriarConta("Usuario Dummy", Guid.NewGuid(), cartao);

            Assert.NotNull(usuario);
            Assert.True(usuario.Playlists.Any());
            Assert.True(usuario.Playlists.First().Nome == "Favoritas");
            Assert.True(usuario.Cartoes.Count == 1);
            Assert.True(usuario.Assinaturas.Count == 1);
            Assert.True(usuario.Assinaturas.First().Plano.Nome == "Plano Dummy");


        }
    }
}
