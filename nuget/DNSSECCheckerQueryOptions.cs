using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.DNSSECChecker
{
    /// <summary>
    /// Query options for the DNSSEC Checker API
    /// </summary>
    public class DNSSECCheckerQueryOptions
    {
        /// <summary>
        /// The domain name to check DNSSEC configuration for
        /// </summary>
        [JsonProperty("domain")]
        public string Domain { get; set; }
    }
}
