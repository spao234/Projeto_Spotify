using SpotifyLike.Domain.Transacao;

namespace SpotifyLike.Repository.Transacao
{
    public interface IPlanoRepository
    {
        Plano GetPlanoById(Guid planoId);
    }
}