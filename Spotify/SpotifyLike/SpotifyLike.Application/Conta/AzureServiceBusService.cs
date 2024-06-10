using Azure.Messaging.ServiceBus;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace SpotifyLike.Application.Conta
{
    public class AzureServiceBusService : IAzureServiceBusService
    {
        private string ConnectionString = "Endpoint=sb://spotify-like-infnet-2024.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=u2Cr1Sd7FJBlTTzgKjpYAQOSAxTn4R31U+ASbHebI2M=";

        public AzureServiceBusService() { }

        public async Task SendMessage(Notificacao notificacao)
        {
            ServiceBusClient client;
            ServiceBusSender sender;

            client = new ServiceBusClient(ConnectionString);

            sender = client.CreateSender("notificacao_queue");

            var body = JsonSerializer.Serialize(notificacao);

            var message = new ServiceBusMessage(body);

            await sender.SendMessageAsync(message);

        }
    }
}
