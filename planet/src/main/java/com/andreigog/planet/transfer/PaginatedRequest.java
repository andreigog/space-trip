package com.andreigog.planet.transfer;

import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PaginatedRequest {
  protected static final int DEFAULT_PAGE = 0;
  protected static final int DEFAULT_PAGE_SIZE = 3;

  @Min(value = 0, message = "dsasda")
  protected Integer page;
  @Min(0)
  @Max(25)
  protected Integer pageSize;

  public Integer getPage() {
    return Optional.ofNullable(page).orElse(DEFAULT_PAGE);
  }

  public Integer getPageSize() {
    return Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
  }
}
