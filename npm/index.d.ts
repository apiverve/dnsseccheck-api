declare module '@apiverve/dnsseccheck' {
  export interface dnsseccheckOptions {
    api_key: string;
    secure?: boolean;
  }

  /**
   * Describes fields the current plan does not unlock. Locked fields arrive as null
   * in `data`; `locked_fields` names them, using dot paths for nested fields.
   * Absent when the plan unlocks everything.
   */
  export interface PremiumInfo {
    message: string;
    upgrade_url: string;
    locked_fields: string[];
  }

  export interface dnsseccheckResponse {
    status: string;
    error: string | null;
    data: DNSSECCheckerData;
    code?: number;
    premium?: PremiumInfo;
  }


  interface DNSSECCheckerData {
      domain:         null | string;
      dnssecEnabled:  boolean | null;
      valid:          boolean | null;
      records:        Records;
      errors:         any[];
      details:        Details;
      status:         null | string;
      recommendation: null | string;
      riskScore:      number | null;
      riskLevel:      null | string;
  }
  
  interface Details {
      dnskeyCount: number | null;
      dsCount:     number | null;
  }
  
  interface Records {
      dnskey: number | null;
      ds:     number | null;
      nsec:   null | string;
  }

  export default class dnsseccheckWrapper {
    constructor(options: dnsseccheckOptions);

    execute(callback: (error: any, data: dnsseccheckResponse | null) => void): Promise<dnsseccheckResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: dnsseccheckResponse | null) => void): Promise<dnsseccheckResponse>;
    execute(query?: Record<string, any>): Promise<dnsseccheckResponse>;
  }
}
