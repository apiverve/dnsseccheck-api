declare module '@apiverve/dnsseccheck' {
  export interface dnsseccheckOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface dnsseccheckResponse {
    status: string;
    error: string | null;
    data: DNSSECCheckerData;
    code?: number;
  }


  interface DNSSECCheckerData {
      domain:         string;
      dnssecEnabled:  boolean;
      valid:          boolean;
      records:        Records;
      errors:         any[];
      details:        Details;
      status:         string;
      recommendation: string;
  }
  
  interface Details {
      dnskeyCount: number;
      dsCount:     number;
  }
  
  interface Records {
      dnskey: number;
      ds:     number;
      nsec:   string;
  }

  export default class dnsseccheckWrapper {
    constructor(options: dnsseccheckOptions);

    execute(callback: (error: any, data: dnsseccheckResponse | null) => void): Promise<dnsseccheckResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: dnsseccheckResponse | null) => void): Promise<dnsseccheckResponse>;
    execute(query?: Record<string, any>): Promise<dnsseccheckResponse>;
  }
}
