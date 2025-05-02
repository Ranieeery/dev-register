package dev.raniery.register.model.developer;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

@Converter
public class LanguagesConverter implements AttributeConverter<EnumSet<Languages>, String> {

    @Override
    public String convertToDatabaseColumn(EnumSet<Languages> languages) {
        if (languages == null || languages.isEmpty()) {
            return "{}";
        }

        String values = languages.stream()
            .map(Languages::name)
            .collect(Collectors.joining(","));

        return "{" + values + "}";
    }

    @Override
    public EnumSet<Languages> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.equals("{}")) {
            return EnumSet.noneOf(Languages.class);
        }

        String content = dbData.substring(1, dbData.length() - 1);
        if (content.isEmpty()) {
            return EnumSet.noneOf(Languages.class);
        }

        String[] languageNames = content.split(",");

        return Arrays.stream(languageNames)
            .map(String::trim)
            .map(Languages::valueOf)
            .collect(Collectors.toCollection(() -> EnumSet.noneOf(Languages.class)));
    }
}