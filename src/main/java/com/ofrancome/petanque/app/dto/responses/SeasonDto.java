package com.ofrancome.petanque.app.dto.responses;

import com.ofrancome.petanque.domain.seasons.Season;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record SeasonDto(Long number, String start, String end, List<SeasonRankingDto> rankings)
        implements Serializable {

    public static SeasonDto from(Season season) {
        return new SeasonDto(
                season.getId(),
                season.getStart().format(DateTimeFormatter.ISO_DATE),
                season.getEnd() != null ? season.getEnd().format(DateTimeFormatter.ISO_DATE) : null,
                season.getRankings().stream().map(SeasonRankingDto::from).toList()
        );
    }
}
