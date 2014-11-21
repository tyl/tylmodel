package it.tylframework.data.mongo.basics;

import it.tylframework.data.mongo.common.Footprint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by mp on 20/11/14.
 */
@Document(collection="bas_country")
@TypeAlias("Country")
@Data
@RequiredArgsConstructor
public class Country{
    @NonNull String twoCharCode;
    @NonNull String officialName;
    @NonNull Integer numericCode;

    @CreatedDate
    private Date createdDate;
}
