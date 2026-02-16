/// Response models for the DNSSEC Checker API.

/// API Response wrapper.
class DnsseccheckResponse {
  final String status;
  final dynamic error;
  final DnsseccheckData? data;

  DnsseccheckResponse({
    required this.status,
    this.error,
    this.data,
  });

  factory DnsseccheckResponse.fromJson(Map<String, dynamic> json) => DnsseccheckResponse(
    status: json['status'] as String? ?? '',
    error: json['error'],
    data: json['data'] != null ? DnsseccheckData.fromJson(json['data']) : null,
  );

  Map<String, dynamic> toJson() => {
    'status': status,
    if (error != null) 'error': error,
    if (data != null) 'data': data,
  };
}

/// Response data for the DNSSEC Checker API.

class DnsseccheckData {
  String? domain;
  bool? dnssecEnabled;
  bool? valid;
  DnsseccheckDataRecords? records;
  List<dynamic>? errors;
  DnsseccheckDataDetails? details;
  String? status;
  String? recommendation;

  DnsseccheckData({
    this.domain,
    this.dnssecEnabled,
    this.valid,
    this.records,
    this.errors,
    this.details,
    this.status,
    this.recommendation,
  });

  factory DnsseccheckData.fromJson(Map<String, dynamic> json) => DnsseccheckData(
      domain: json['domain'],
      dnssecEnabled: json['dnssecEnabled'],
      valid: json['valid'],
      records: json['records'] != null ? DnsseccheckDataRecords.fromJson(json['records']) : null,
      errors: (json['errors'] as List?)?.cast<dynamic>(),
      details: json['details'] != null ? DnsseccheckDataDetails.fromJson(json['details']) : null,
      status: json['status'],
      recommendation: json['recommendation'],
    );
}

class DnsseccheckDataRecords {
  int? dnskey;
  int? ds;
  String? nsec;

  DnsseccheckDataRecords({
    this.dnskey,
    this.ds,
    this.nsec,
  });

  factory DnsseccheckDataRecords.fromJson(Map<String, dynamic> json) => DnsseccheckDataRecords(
      dnskey: json['dnskey'],
      ds: json['ds'],
      nsec: json['nsec'],
    );
}

class DnsseccheckDataDetails {
  int? dnskeyCount;
  int? dsCount;

  DnsseccheckDataDetails({
    this.dnskeyCount,
    this.dsCount,
  });

  factory DnsseccheckDataDetails.fromJson(Map<String, dynamic> json) => DnsseccheckDataDetails(
      dnskeyCount: json['dnskeyCount'],
      dsCount: json['dsCount'],
    );
}

class DnsseccheckRequest {
  String domain;

  DnsseccheckRequest({
    required this.domain,
  });

  Map<String, dynamic> toJson() => {
      'domain': domain,
    };
}
