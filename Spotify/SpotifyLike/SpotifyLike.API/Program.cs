using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Polly.Registry;
using SpotifyLike.API;
using SpotifyLike.Application.Conta;
using SpotifyLike.Repository;
using SpotifyLike.Repository.Conta;
using SpotifyLike.Repository.Streaming;
using SpotifyLike.Repository.Transacao;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();



builder.Services.AddHttpClient("musicaApiServer", client =>
{
    client.BaseAddress = new Uri("http://localhost:8080");
}).AddPolicyHandler(RetryPolicyConfiguration.GetRetryPolicy());


//Configurando banco de dados
builder.Services.AddDbContext<SpotifyContext>(c =>
{
    c.UseInMemoryDatabase("SpotifyLike");
});


//Adicionando injeção de dependencia Repository
builder.Services.AddScoped<IUsuarioRepository, UsuarioRepository>();
builder.Services.AddScoped<IBandaRepository, BandaRepository>();
builder.Services.AddScoped<IPlanoRepository, PlanoRepository>();
builder.Services.AddScoped<IAzureServiceBusService, AzureServiceBusService>();

//Services
builder.Services.AddScoped<UsuarioService>();
builder.Services.AddApplicationInsightsTelemetry(new Microsoft.ApplicationInsights.AspNetCore.Extensions.ApplicationInsightsServiceOptions
{
    ConnectionString = builder.Configuration["APPLICATIONINSIGHTS_CONNECTION_STRING"]
});


var app = builder.Build();

// Configure the HTTP request pipeline.
app.UseSwagger();
app.UseSwaggerUI();


app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();


