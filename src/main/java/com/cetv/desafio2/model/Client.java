package com.cetv.desafio2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Client
 */
@Validated
public class Client   {
  @NotEmpty(message = "El nombre es un campo requerido")
  @Pattern(regexp="[a-zA-Z\\s]+", message="El formato de la nombre debe ser una cadena")
  @JsonProperty("nombre")
  private String nombre = null;
  @NotEmpty(message = "El apellido es un campo requerido")
  @JsonProperty("apellido")
  @Pattern(regexp="[a-zA-Z\\s]+", message="El formato de la apellido debe ser una cadena")
  private String apellido = null;
  @NotNull(message = "La edad es un campo requerido")
  @JsonProperty("edad")
  private Integer edad = null;
  @NotEmpty(message = "la fecha de nacimiento es un campo requerido")
  @JsonProperty("fecNac")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message="El formato de la fecha debe ser yyyy-MM-dd")
  private String fecNac = null;

  private String fecMuerte;


  public Client nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String getFecMuerte() {
    return fecMuerte;
  }

  /**
   * Get nombre
   * @return nombre
  **/



  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Client apellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  /**
   * Get apellido
   * @return apellido
  **/



  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public Client edad(Integer edad) {
    this.edad = edad;
    return this;
  }

  /**
   * Get edad
   * @return edad
  **/



  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }

  public Client fecNac(String fecNac) {
    this.fecNac = fecNac;
    return this;
  }

  /**
   * Get fecNac
   * @return fecNac
  **/



  public String getFecNac() {
    return fecNac;
  }

  public void setFecNac(String fecNac) {
    this.fecNac = fecNac;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return Objects.equals(this.nombre, client.nombre) &&
        Objects.equals(this.apellido, client.apellido) &&
        Objects.equals(this.edad, client.edad) &&
        Objects.equals(this.fecNac, client.fecNac);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, apellido, edad, fecNac);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Client {\n");
    
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    apellido: ").append(toIndentedString(apellido)).append("\n");
    sb.append("    edad: ").append(toIndentedString(edad)).append("\n");
    sb.append("    fecNac: ").append(toIndentedString(fecNac)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public void setFecMuerte(String fecMuerte) {
    this.fecMuerte = fecMuerte;
  }


}

