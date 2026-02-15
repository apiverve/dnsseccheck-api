// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     DNSSECCheckerData data = Converter.fromJsonString(jsonString);

package com.apiverve.dnsseccheck.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static DNSSECCheckerData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(DNSSECCheckerData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(DNSSECCheckerData.class);
        writer = mapper.writerFor(DNSSECCheckerData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// DNSSECCheckerData.java

package com.apiverve.dnsseccheck.data;

import com.fasterxml.jackson.annotation.*;

public class DNSSECCheckerData {
    private String domain;
    private boolean dnssecEnabled;
    private boolean valid;
    private Records records;
    private Object[] errors;
    private Details details;
    private String status;
    private String recommendation;

    @JsonProperty("domain")
    public String getDomain() { return domain; }
    @JsonProperty("domain")
    public void setDomain(String value) { this.domain = value; }

    @JsonProperty("dnssecEnabled")
    public boolean getDnssecEnabled() { return dnssecEnabled; }
    @JsonProperty("dnssecEnabled")
    public void setDnssecEnabled(boolean value) { this.dnssecEnabled = value; }

    @JsonProperty("valid")
    public boolean getValid() { return valid; }
    @JsonProperty("valid")
    public void setValid(boolean value) { this.valid = value; }

    @JsonProperty("records")
    public Records getRecords() { return records; }
    @JsonProperty("records")
    public void setRecords(Records value) { this.records = value; }

    @JsonProperty("errors")
    public Object[] getErrors() { return errors; }
    @JsonProperty("errors")
    public void setErrors(Object[] value) { this.errors = value; }

    @JsonProperty("details")
    public Details getDetails() { return details; }
    @JsonProperty("details")
    public void setDetails(Details value) { this.details = value; }

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

    @JsonProperty("recommendation")
    public String getRecommendation() { return recommendation; }
    @JsonProperty("recommendation")
    public void setRecommendation(String value) { this.recommendation = value; }
}

// Details.java

package com.apiverve.dnsseccheck.data;

import com.fasterxml.jackson.annotation.*;

public class Details {
    private long dnskeyCount;
    private long dsCount;

    @JsonProperty("dnskeyCount")
    public long getDnskeyCount() { return dnskeyCount; }
    @JsonProperty("dnskeyCount")
    public void setDnskeyCount(long value) { this.dnskeyCount = value; }

    @JsonProperty("dsCount")
    public long getDsCount() { return dsCount; }
    @JsonProperty("dsCount")
    public void setDsCount(long value) { this.dsCount = value; }
}

// Records.java

package com.apiverve.dnsseccheck.data;

import com.fasterxml.jackson.annotation.*;

public class Records {
    private long dnskey;
    private long ds;
    private String nsec;

    @JsonProperty("dnskey")
    public long getDnskey() { return dnskey; }
    @JsonProperty("dnskey")
    public void setDnskey(long value) { this.dnskey = value; }

    @JsonProperty("ds")
    public long getDs() { return ds; }
    @JsonProperty("ds")
    public void setDs(long value) { this.ds = value; }

    @JsonProperty("nsec")
    public String getNsec() { return nsec; }
    @JsonProperty("nsec")
    public void setNsec(String value) { this.nsec = value; }
}