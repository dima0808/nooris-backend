package nu.nooris.noorisbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Data transfer object for user login")
public class LoginDto {

  @NotBlank(message = "Username is mandatory")
  @Schema(description = "Username of the user", example = "admin")
  private String username;

  @NotBlank(message = "Password is mandatory")
  @Schema(description = "Password of the user", example = "password123")
  private String password;
}
