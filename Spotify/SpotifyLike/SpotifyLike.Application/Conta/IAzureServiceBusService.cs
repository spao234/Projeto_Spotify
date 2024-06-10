
namespace SpotifyLike.Application.Conta
{
    public interface IAzureServiceBusService
    {
        Task SendMessage(Notificacao notificacao);
    }
}