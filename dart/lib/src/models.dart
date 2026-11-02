/// Response models for the DNSSEC Checker API.

/// API Response wrapper.
class DnsseccheckResponse {
  final String status;
  final dynamic error;
  final DnsseccheckData? data;

  /// Set only when the current plan locks one or more fields; null otherwise.
  final PremiumInfo? premium;

  DnsseccheckResponse({
    required this.status,
    this.error,
    this.data,
    this.premium,
  });

  factory DnsseccheckResponse.fromJson(Map<String, dynamic> json) => DnsseccheckResponse(
    status: json['status'] as String? ?? '',
    error: json['error'],
    data: json['data'] != null ? DnsseccheckData.fromJson(json['data']) : null,
    premium: json['premium'] != null ? PremiumInfo.fromJson(json['premium']) : null,
  );

  Map<String, dynamic> toJson() => {
    'status': status,
    if (error != null) 'error': error,
    if (data != null) 'data': data,
    if (premium != null) 'premium': premium!.toJson(),
  };
}

/// Describes fields the current plan does not unlock. Locked fields arrive as null
/// in [data], and [lockedFields] names them (nested ones use dot paths).
class PremiumInfo {
  final String? message;
  final String? upgradeUrl;
  final List<String>? lockedFields;

  PremiumInfo({this.message, this.upgradeUrl, this.lockedFields});

  factory PremiumInfo.fromJson(Map<String, dynamic> json) => PremiumInfo(
    message: json['message'] as String?,
    upgradeUrl: json['upgrade_url'] as String?,
    lockedFields: (json['locked_fields'] as List?)?.map((e) => e.toString()).toList(),
  );

  Map<String, dynamic> toJson() => {
    if (message != null) 'message': message,
    if (upgradeUrl != null) 'upgrade_url': upgradeUrl,
    if (lockedFields != null) 'locked_fields': lockedFields,
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
  num? riskScore;
  String? riskLevel;

  DnsseccheckData({
    this.domain,
    this.dnssecEnabled,
    this.valid,
    this.records,
    this.errors,
    this.details,
    this.status,
    this.recommendation,
    this.riskScore,
    this.riskLevel,
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
      riskScore: json['riskScore'],
      riskLevel: json['riskLevel'],
    );
}

class DnsseccheckDataRecords {
  num? dnskey;
  num? ds;
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
  num? dnskeyCount;
  num? dsCount;

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
