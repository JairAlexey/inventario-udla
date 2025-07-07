# Reporte de Auditoría: Revisión Manual vs. SonarLint

| ID | Problema Detectado (SonarLint) | Análisis Manual / Causa Raíz | Acción Recomendada (Issue) |
|---|---|---|---|
| S106 | Uso de `System.out` en 6 lugares. | La clase `Inventory` mezcla lógica de negocio con la presentación en consola, violando el Principio de Responsabilidad Única (SRP). | Crear Issue para refactorizar y que los métodos retornen valores. |
| S1135 | Comentario `TODO` / Tarea pendiente. | El comentario era ambiguo y reveló la existencia de código duplicado (`getTotalValue` y `totalInventoryValue`). | Crear Issue para eliminar el método duplicado y estandarizar. |