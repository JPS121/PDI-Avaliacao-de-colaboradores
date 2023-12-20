import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import IPdi from "../../../../interface/IPdi";


export default function ListaPdi() {

  const parametros = useParams()

  const [pdi, setPdi] = useState<IPdi[]>([]);
  const [pagina, setPagina] = useState(0);
  const [limite, setLimite] = useState(10);

  useEffect(() => {
    axios.get(`http://localhost:8081/pdi/${parametros.id}?page=${pagina}`)
      .then(resposta => {
        setPdi(resposta.data.content)
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

  return (
    <div>
      <h2>Funcionários</h2>
      <ul>
        {pdi.map((pdi, index) => (
          <li key={pdi.pdi_id}>
            <div>Nome: {pdi.funcionario.nome}</div>
            <div>Avaliação: {pdi.avaliacao}</div>
          </li>
        ))}
      </ul>
      <button onClick={() => paginacao(-1)}>Anterior</button>
      <button onClick={() => paginacao(1)} >Próxima</button>
    </div>
  )

}