# DNSSEC Checker API - Ruby Gem

DNSSEC Checker validates the DNSSEC (Domain Name System Security Extensions) configuration of a domain. It verifies that DNS responses are authenticated and haven't been tampered with.

## Installation

Add this line to your application's Gemfile:

```ruby
gem 'apiverve_dnsseccheck'
```

And then execute:

```bash
$ bundle install
```

Or install it yourself as:

```bash
$ gem install apiverve_dnsseccheck
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```ruby
require 'apiverve_dnsseccheck'

# Initialize the client
client = APIVerve::Dnsseccheck::Client.new(api_key: "YOUR_API_KEY")

# Make a request
response = client.execute({ domain: "cloudflare.com" })

# Print the response
puts response
```


### Error Handling

```ruby
begin
  response = client.execute({ domain: "cloudflare.com" })
  puts response["data"]
rescue APIVerve::Dnsseccheck::ValidationError => e
  puts "Validation error: #{e.errors.join(', ')}"
rescue APIVerve::Dnsseccheck::APIError => e
  puts "API error: #{e.message}"
  puts "Status code: #{e.status_code}"
end
```

### Debug Mode

```ruby
# Enable debug logging
client = APIVerve::Dnsseccheck::Client.new(
  api_key: "YOUR_API_KEY",
  debug: true
)
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

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/dnsseccheck?utm_source=rubygems&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/dnsseccheck?utm_source=ruby&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/dnsseccheck?utm_source=ruby&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This gem is available under the [MIT License](LICENSE).
