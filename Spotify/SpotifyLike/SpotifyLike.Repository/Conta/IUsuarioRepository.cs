using SpotifyLike.Domain.Conta;

namespace SpotifyLike.Repository.Conta
{
    public interface IUsuarioRepository
    {
        Usuario GetUsuario(Guid id);
        void Remove(Usuario usuario);
        void Save(Usuario usuario);
        void Update(Usuario usuario);
    }
}