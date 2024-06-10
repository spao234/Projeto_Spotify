using SpotifyLike.Domain.Streaming;

namespace SpotifyLike.Repository.Streaming
{
    public interface IBandaRepository
    {
        Musica GetMusica(Guid idMusica);
    }
}