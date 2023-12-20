import IFuncionario from "./IFuncionario";

export default interface IPdi {
  pdi_id: number;
  funcionario: IFuncionario;
  funcionario_id: number;
  avaliacao: string;
  data: Date;
}