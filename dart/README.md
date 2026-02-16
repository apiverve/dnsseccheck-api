# DNSSEC Checker API - Dart/Flutter Client

DNSSEC Checker validates the DNSSEC (Domain Name System Security Extensions) configuration of a domain. It verifies that DNS responses are authenticated and haven't been tampered with.

[![pub package](https://img.shields.io/pub/v/apiverve_dnsseccheck.svg)](https://pub.dev/packages/apiverve_dnsseccheck)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is the Dart/Flutter client for the [DNSSEC Checker API](https://apiverve.com/marketplace/dnsseccheck?utm_source=dart&utm_medium=readme).

## Installation

Add this to your `pubspec.yaml`:

```yaml
dependencies:
  apiverve_dnsseccheck: ^1.1.14
```

Then run:

```bash
dart pub get
# or for Flutter
flutter pub get
```

## Usage

```dart
import 'package:apiverve_dnsseccheck/apiverve_dnsseccheck.dart';

void main() async {
  final client = DnsseccheckClient('YOUR_API_KEY');

  try {
    final response = await client.execute({
      'domain': 'cloudflare.com'
    });

    print('Status: ${response.status}');
    print('Data: ${response.data}');
  } catch (e) {
    print('Error: $e');
  }
}
```

## Response

```json
{
  "status": "ok",
  "error": null,
  "data": {
    "domain": "cloudflare.com",
    "dnssecEnabled": true,
    "valid": true,
    "records": {
      "dnskey": 3,
      "ds": 2,
      "nsec": "NSEC3"
    },
    "errors": [],
    "details": {
      "dnskeyCount": 3,
      "dsCount": 2
    },
    "status": "DNSSEC is properly configured with DS records at parent",
    "recommendation": "DNSSEC is properly configured"
  }
}
```

## API Reference

- **API Home:** [DNSSEC Checker API](https://apiverve.com/marketplace/dnsseccheck?utm_source=dart&utm_medium=readme)
- **Documentation:** [docs.apiverve.com/ref/dnsseccheck](https://docs.apiverve.com/ref/dnsseccheck?utm_source=dart&utm_medium=readme)

## Authentication

All requests require an API key. Get yours at [apiverve.com](https://apiverve.com?utm_source=dart&utm_medium=readme).

## License

MIT License - see [LICENSE](LICENSE) for details.

---

Built with Dart for [APIVerve](https://apiverve.com?utm_source=dart&utm_medium=readme)
