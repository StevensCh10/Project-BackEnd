package projeto.redes2.project.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { })
@DecimalMin("18")
public @interface Age {
	
	@OverridesAttribute(constraint=DecimalMin.class, name="message") //Informando ao spring que o atributo message padrão da classe DecimalMin será substituido 
	String message() default "{Invalid.Age}";						 //pelo que está sendo declarado nessa classe.

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	 
}
