const mongoose = require('mongoose');

const FestivoSchema = new mongoose.Schema({
    nombre: String,
    dia: Number,
    mes: Number,
    diasPascua: Number,
    idTipo: Number
}, { collection: 'festivos', versionKey: false });

module.exports = mongoose.model('Festivo', FestivoSchema);