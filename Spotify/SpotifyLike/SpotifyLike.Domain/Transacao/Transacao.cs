using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpotifyLike.Domain.Transacao
{
    public class Transacao
    {
        public Guid Id { get; set; }
        public DateTime DtTransacao { get; set; }
        public Decimal Valor { get; set; }
        
        //TODO: Corrigir
        public String Merchant { get; set; }
        public String Descricao { get; set; }
    }
}
