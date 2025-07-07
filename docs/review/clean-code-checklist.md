# Checklist de Revisión de Clean Code

## Bloque 1 – Nomenclatura
- [ ] Nombres de variables, métodos y clases son claros, expresivos y en inglés.
- [ ] Los nombres de los métodos siguen el formato verbo + objeto (e.g., `calculateTotalValue`).
- [ ] Evita abreviaturas o nombres confusos (e.g., `p`, `q`).

## Bloque 2 – Tamaño y Responsabilidad
- [ ] Los métodos tienen una longitud menor o igual a 20 líneas.
- [ ] Cada clase y método tiene una única responsabilidad (Principio de Responsabilidad Única - SRP).
- [ ] La lógica de negocio no se mezcla con la presentación en consola (`System.out.println`).

## Bloque 3 – Estructura y Legibilidad
- [ ] El código tiene sangrías y formato consistentes.
- [ ] No hay comentarios que expliquen código poco claro. El código debe ser autoexplicativo.
- [ ] Se utiliza el encapsulamiento adecuadamente (e.g., una clase `Product` en lugar de listas paralelas).

## Bloque 4 – Manejo de Errores y Robustez
- [ ] Se validan las entradas para prevenir datos inválidos (e.g., cantidades negativas, precios negativos).
- [ ] Los mensajes de error son claros y descriptivos.