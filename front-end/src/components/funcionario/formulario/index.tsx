import axios from "axios";
import React, { useEffect, useState } from "react"
import { useParams } from "react-router-dom";
import IFuncionario from "../../../interface/IFuncionario";

export default function FomularioFuncionario() {

  const parametros = useParams()

  useEffect(() => {
    if (parametros.id) {
      axios.get<IFuncionario[]>(`http://localhost:8081/funcionario/${parametros.id}`)
        .then(resposta => {
          console.log(resposta)
          setNome(resposta.data[0].nome)
          setEmail(resposta.data[0].email)
          setCargo(resposta.data[0].cargo)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }, [parametros])

  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cargo, setCargo] = useState('');

  const aoSubmeterFormulario = (evento: React.FormEvent<HTMLFormElement>) => {
    evento.preventDefault()

    if (parametros.id) {
      axios.put("http://localhost:8081/funcionario", { id: parametros.id, nome: nome, email: email, cargo: cargo })
        .then(() => { alert("Funcionário atualizado com sucesso!") })
        .catch(err => { console.log(err) })
      console.log(nome)
      console.log(email)
      console.log(cargo)
    } else {
      axios.post("http://localhost:8081/funcionario", { nome: nome, email: email, cargo: cargo })
        .then(() => { alert("Funcionário cadastrado com sucesso!") })
        .catch(err => { console.log(err) })
      console.log(nome)
      console.log(email)
      console.log(cargo)
    }
  }

  return (

    <form onSubmit={aoSubmeterFormulario}>
      <label htmlFor="nome">Nome do funcionário</label>
      <div>
        <input value={nome} onChange={evento => setNome(evento.target.value)} type="text" name="nome" id="nome" placeholder="digite o nome do funcionário" required />
      </div>
      <label htmlFor="email">E-mail do funcionário</label>
      <div>
        <input value={email} onChange={evento => setEmail(evento.target.value)} type="email" name="email" id="email" placeholder="digite o e-mail do funcionário" required />
      </div>
      <label htmlFor="cargo">Cargo do funcionário</label>
      <div>
        <input value={cargo} onChange={evento => setCargo(evento.target.value)} type="text" name="cargo" id="cargo" placeholder="digite o cargo do funcionário" required />
      </div>
      <button type="submit">
        Enviar
      </button>
    </form>
  )
}