#See https://aka.ms/customizecontainer to learn how to customize your debug container and how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
USER app
WORKDIR /app
EXPOSE 8080
EXPOSE 8081
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
ARG BUILD_CONFIGURATION=Release
WORKDIR /src
COPY ["SpotifyLike.API/SpotifyLike.API.csproj", "SpotifyLike.API/"]
COPY ["SpotifyLike.Application/SpotifyLike.Application.csproj", "SpotifyLike.Application/"]
COPY ["SpotifyLike.Domain/SpotifyLike.Domain.csproj", "SpotifyLike.Domain/"]
COPY ["SpotifyLike.Repository/SpotifyLike.Repository.csproj", "SpotifyLike.Repository/"]
COPY . .
WORKDIR "/src/SpotifyLike.API"
RUN dotnet publish "./SpotifyLike.API.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=build	 /app/publish .
ENTRYPOINT ["dotnet", "SpotifyLike.API.dll"]