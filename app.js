const express = require('express');
const mongoose = require('mongoose');
const Festivo = require('./models/Festivo');
require('dotenv').config();

const app = express();
app.use(express.json());

// Conexión a MongoDB
mongoose.connect(process.env.MONGO_URI)
    .then(() => console.log("✅ Conectado a MongoDB"))
    .catch(err => console.error(" Error:", err));

// --- LÓGICA MATEMÁTICA DEL EXAMEN ---
function obtenerDomingoPascua(ano) {
    const a = ano % 19;
    const b = ano % 4;
    const c = ano % 7;
    const d = (19 * a + 24) % 30;
    const dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;
    
    // El Domingo de Ramos es 15 de marzo + dias
    let fecha = new Date(ano, 2, 15);
    fecha.setDate(fecha.getDate() + dias);
    // El Domingo de Pascua es 7 días después del de Ramos
    fecha.setDate(fecha.getDate() + 7);
    return fecha;
}

function siguienteLunes(fecha) {
    const diaSemana = fecha.getDay(); // 0 es Domingo, 1 Lunes...
    if (diaSemana === 0) fecha.setDate(fecha.getDate() + 1);
    else if (diaSemana !== 1) fecha.setDate(fecha.getDate() + (8 - diaSemana));
    return fecha;
}

// --- RUTA SOLICITADA EN EL EXAMEN ---
app.get('/api/festivos/verificar/:ano/:mes/:dia', async (req, res) => {
    const { ano, mes, dia } = req.params;
    const fechaUsuario = new Date(ano, mes - 1, dia);

    // Validar fecha real
    if (isNaN(fechaUsuario.getTime()) || fechaUsuario.getDate() != dia) {
        return res.send("Fecha No valida"); // [cite: 425]
    }

    const pascua = obtenerDomingoPascua(parseInt(ano));
    const festivos = await Festivo.find();
    let esFestivo = false;

    festivos.forEach(f => {
        let fechaFestivo;
        if (f.idTipo === 1) { // Fijo [cite: 429]
            fechaFestivo = new Date(ano, f.mes - 1, f.dia);
        } else if (f.idTipo === 2) { // Ley Puente [cite: 434]
            fechaFestivo = siguienteLunes(new Date(ano, f.mes - 1, f.dia));
        } else if (f.idTipo === 3) { // Basado en Pascua [cite: 439]
            fechaFestivo = new Date(pascua);
            fechaFestivo.setDate(fechaFestivo.getDate() + f.diasPascua);
        } else if (f.idTipo === 4) { // Pascua + Ley Puente [cite: 448, 449]
            fechaFestivo = new Date(pascua);
            fechaFestivo.setDate(fechaFestivo.getDate() + f.diasPascua);
            fechaFestivo = siguienteLunes(fechaFestivo);
        }

        if (fechaUsuario.getTime() === fechaFestivo?.getTime()) esFestivo = true;
    });

    res.send(esFestivo ? "Es Festivo" : "No es festivo"); // [cite: 359, 394]
});

const PORT = 8080;
app.listen(PORT, () => {
    console.log("-----------------------------------------");
    console.log(` SERVIDOR LISTO EN: http://localhost:${PORT}`);
    console.log("-----------------------------------------");
});

mongoose.connection.on('connected', () => {
    console.log(" CONECTADO EXITOSAMENTE A MONGODB");
});