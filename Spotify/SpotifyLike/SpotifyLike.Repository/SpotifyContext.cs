using Microsoft.EntityFrameworkCore;
using SpotifyLike.Domain.Conta;
using SpotifyLike.Domain.Streaming;
using SpotifyLike.Domain.Transacao;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpotifyLike.Repository
{
    public class SpotifyContext : DbContext
    {

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Plano> Planos { get; set; }

        public SpotifyContext(DbContextOptions<SpotifyContext> options) : base(options)
        {
                
        }
    }
}
