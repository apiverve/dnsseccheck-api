# DNSSEC Checker API - PHP Package

DNSSEC Checker validates the DNSSEC (Domain Name System Security Extensions) configuration of a domain. It verifies that DNS responses are authenticated and haven't been tampered with.

## Installation

Install via Composer:

```bash
composer require apiverve/dnsseccheck
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```php
<?php

require_once 'vendor/autoload.php';

use APIVerve\Dnsseccheck\Client;

// Initialize the client
$client = new Client('YOUR_API_KEY');

// Make a request
$response = $client->execute(['domain' => 'cloudflare.com']);

// Print the response
print_r($response);
```


### Error Handling

```php
use APIVerve\Dnsseccheck\Client;
use APIVerve\Dnsseccheck\Exceptions\APIException;
use APIVerve\Dnsseccheck\Exceptions\ValidationException;

try {
    $response = $client->execute(['domain' => 'cloudflare.com']);
    print_r($response['data']);
} catch (ValidationException $e) {
    echo "Validation error: " . implode(', ', $e->getErrors());
} catch (APIException $e) {
    echo "API error: " . $e->getMessage();
    echo "Status code: " . $e->getStatusCode();
}
```

### Debug Mode

```php
// Enable debug logging
$client = new Client(
    apiKey: 'YOUR_API_KEY',
    debug: true
);
```

## Example Response

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
    "recommendation": "DNSSEC is properly configured",
    "riskScore": 5,
    "riskLevel": "low"
  }
}
```

## Requirements

- PHP 7.4 or higher
- Guzzle HTTP client

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/dnsseccheck?utm_source=packagist&utm_medium=readme).

## Support

- Website: [https://apiverve.com/marketplace/dnsseccheck?utm_source=php&utm_medium=readme](https://apiverve.com/marketplace/dnsseccheck?utm_source=php&utm_medium=readme)
- Email: hello@apiverve.com

## License

This package is available under the [MIT License](LICENSE).
