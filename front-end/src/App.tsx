import React from 'react';
import { Routes, Route } from 'react-router-dom';
import FormularioFuncionario from './components/funcionario/formulario';
import ListaFuncionarios from './components/funcionario/lista';

import './App.css';
import ListaPdi from './components/funcionario/pdi/lista';

function App() {
  return (
    <Routes>
      <Route path="/" element={<ListaFuncionarios />} />
      <Route path="/funcionario/novo" element={<FormularioFuncionario />} />
      <Route path="/funcionario/editar/:id" element={<FormularioFuncionario />} />
      <Route path="/funcionario/pdi/:id" element={<ListaPdi />} />
    </Routes>
  );
}

export default App;
