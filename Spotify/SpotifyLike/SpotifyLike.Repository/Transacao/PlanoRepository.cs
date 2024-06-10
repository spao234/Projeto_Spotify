using SpotifyLike.Domain.Transacao;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpotifyLike.Repository.Transacao
{
    public class PlanoRepository : IPlanoRepository
    {
        private SpotifyContext spotifyContext;

        public PlanoRepository(SpotifyContext spotifyContext)
        {
            this.spotifyContext = spotifyContext;
        }

        public Plano GetPlanoById(Guid planoId)
        {
            return new Plano()
            {
                Id = new Guid("6a324c2b-1ba9-4d84-a0e7-8d6d0cc2c6e7"),
                Nome = "Plano Basico",
                Descricao = "Plano basico spotify com anuncios",
                Valor = 29.99M
            };
        }
    }
}
