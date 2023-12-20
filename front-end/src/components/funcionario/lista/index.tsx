import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import IFuncionario from "../../../interface/IFuncionario";


export default function ListaFuncionarios() {

  const parametros = useParams()

  const [funcionario, setFuncionario] = useState<IFuncionario[]>([]);
  const [pagina, setPagina] = useState(0);
  const [limite, setLimite] = useState(10);

  useEffect(() => {
    axios.get(`http://localhost:8081/funcionario?page=${pagina}`)
      .then(resposta => {
        setFuncionario(resposta.data.content)
        setLimite(resposta.data.totalPages)
      })
      .catch(err => {
        console.log(err)
      })
  }, [pagina, parametros]);

  const paginacao = (numero: number) => {
    if (pagina + numero >= limite || pagina + numero < 0) return;
    setPagina(pagina + numero);
  }
  const excluirFuncionario = (funcionarioExcluido: IFuncionario) => {
    axios.delete(`http://localhost:8081/funcionario/${funcionarioExcluido.id}`)
      .then(() => {
        const listaFuncionararios = funcionario.filter(funcionario => funcionario.id !== funcionarioExcluido.id)
        setFuncionario([...listaFuncionararios])
      })
  }

  return (
    <div>
      <h2>Funcionários</h2>
      <ul>
        {funcionario.map((funcionario, index) => (
          <li key={funcionario.id}>
            <div>ID: {funcionario.id}</div>
            <div>Nome: {funcionario.nome}</div>
            <div>E-mail: {funcionario.email}</div>
            <div>Cargo: {funcionario.cargo}</div>
            [ <Link to={`/funcionario/editar/${funcionario.id}`}>Editar</Link> ]
            <button onClick={() => excluirFuncionario(funcionario)}>Excluir</button>
            [ <Link to={`http://localhost:8081/pdi/${funcionario.id}`} >Histórico PDI</Link> ]
          </li>
        ))}
      </ul>
      <button onClick={() => paginacao(-1)}>Anterior</button>
      <button onClick={() => paginacao(1)} >Próxima</button>
    </div>
  )
}