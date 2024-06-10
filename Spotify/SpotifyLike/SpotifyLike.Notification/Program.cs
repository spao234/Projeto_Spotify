﻿// See https://aka.ms/new-console-template for more information

using Azure.Messaging.ServiceBus;

string ConnectionString = "Endpoint=sb://spotify-like-infnet-2024.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=u2Cr1Sd7FJBlTTzgKjpYAQOSAxTn4R31U+ASbHebI2M=";

ServiceBusClient client;
ServiceBusProcessor processor;


client = new ServiceBusClient(ConnectionString);

processor = client.CreateProcessor("notificacao_queue");

processor.ProcessMessageAsync += MessageHandler;
processor.ProcessErrorAsync += ErrorHandler;

await processor.StartProcessingAsync();

Console.WriteLine("Processando mensagem. Aperte qualquer tecla para sair");
Console.ReadKey();

await processor.StopProcessingAsync();  


async Task MessageHandler(ProcessMessageEventArgs args)
{
    string body = args.Message.Body.ToString();
    Console.WriteLine($"Received: {body}");

    // complete the message. message is deleted from the queue. 
    await args.CompleteMessageAsync(args.Message);
}

Task ErrorHandler(ProcessErrorEventArgs args)
{
    Console.WriteLine(args.Exception.ToString());
    return Task.CompletedTask;
}




