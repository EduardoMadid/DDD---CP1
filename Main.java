import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Iniciando o Scanner
        Scanner input = new Scanner(System.in);

        // Inicialização das Variáveis
        boolean lotado = false;
        String placa = "", marca = "", modelo = "";
        int tempoChegada = 0, opcao = 0;

        // Menu com as opções
        while (opcao != 4) {
            System.out.println("\n=====================================");
            System.out.println("      SISTEMA DE ESTACIONAMENTO      ");
            System.out.println("=====================================");
            System.out.println("| 1 - Cadastrar carro               |");
            System.out.println("| 2 - Informações do carro          |");
            System.out.println("| 3 - Gerar pagamento               |");
            System.out.println("| 4 - Sair do sistema               |");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");
            opcao = input.nextInt();

            // Opção de Cadastrar Carro
            switch (opcao) {
                case 1:
                    // Se estiver lotado, não permite um cadastro de novos carros
                    if (lotado) {
                        System.out.println("Já existe um carro no estacionamento");
                        break;
                    }

                    // Se não estiver lotado, pede as informações do carro
                    System.out.println("Digite a placa do carro: ");
                    placa = input.next();
                    System.out.println("Digite a marca do carro: ");
                    marca = input.next();
                    System.out.println("Digite o modelo do carro: ");
                    modelo = input.next();

                    // Verificação dos dados de Hora
                    int horaChegada;
                    do {
                        System.out.print("\nDigite a hora de chegada (0 a 23): ");
                        horaChegada = input.nextInt();
                        if (horaChegada < 0 || horaChegada > 23) {
                            System.out.println("❌ Hora inválida! Por favor, digite uma hora entre 0 e 23.");
                        }
                    } while (horaChegada < 0 || horaChegada > 23);
                    horaChegada *= 60;

                    // Verificação dos dados de Minuto
                    int minutosChegada;
                    do {
                        System.out.print("\nDigite os minutos de chegada (0 a 59): ");
                        minutosChegada = input.nextInt();
                        if (minutosChegada < 0 || minutosChegada > 59) {
                            System.out.println("❌ Minutos inválidos! Por favor, digite minutos entre 0 e 59.");
                        }
                    } while (minutosChegada < 0 || minutosChegada > 59);


                    // Tempo total de chegada
                    tempoChegada = horaChegada + minutosChegada;

                    // Mostrando as informações de chegada do carro
                    System.out.println("\n🚗 CADASTRO CONFIRMADO 🚗");
                    System.out.println("=========================================================");
                    System.out.println("✅ Placa do carro: " + placa);
                    System.out.println("⏰ Hora de chegada: " + (tempoChegada / 60) + "h " + (tempoChegada % 60) + "m");
                    System.out.println("=========================================================\n");

                    // Um carro está presente no estacionamento
                    lotado = true;
                    break;

                // Opção de mostrar as informações do carro
                case 2:
                    // Se não existe um carro, avisa que não tem nenhum carro
                    if (!lotado) {
                        System.out.println("Não há carro cadastrado.");
                    } else {
                        // Mostra as informações do carro estacionado
                        System.out.println("\n🚗 INFORMAÇÕES DO CARRO 🚗");
                        System.out.println("=========================================================");
                        System.out.println("📌 Placa:   " + placa);
                        System.out.println("🏷️ Marca:   " + marca);
                        System.out.println("🚘 Modelo:  " + modelo);
                        System.out.println("⏰ Chegada: " + (tempoChegada / 60) + "h " + (tempoChegada % 60) + "m");
                        System.out.println("=========================================================\n");

                    }
                    break;

                // Opção de gerar pagamento
                case 3:
                    // Se não tiver nenhum carro, não é possível realizar a cobrança
                    if (!lotado) {
                        System.out.println("Não tem carro a ser cobrado.");
                        break;
                    }

                    // Verificação do dado de Hora da Saída
                    int horaSaida;
                    do {
                        System.out.print("Digite a hora de saída (0 a 23): ");
                        horaSaida = input.nextInt();
                        if (horaSaida < 0 || horaSaida > 23) {
                            System.out.println("❌ Hora inválida! Por favor, digite uma hora entre 0 e 23.");
                        }
                    } while (horaSaida < 0 || horaSaida > 23);
                    horaSaida *= 60;

                    // Verificação do dado de Minuto da Saída
                    int minutosSaida;
                    do {
                        System.out.print("Digite os minutos de saída (0 a 59): ");
                        minutosSaida = input.nextInt();
                        if (minutosSaida < 0 || minutosSaida > 59) {
                            System.out.println("❌ Minutos inválidos! Por favor, digite minutos entre 0 e 59.");
                        }
                    } while (minutosSaida < 0 || minutosSaida > 59);

                    // Calcula o horário de sáida do carro
                    int tempoSaida = horaSaida + minutosSaida;

                    // Pergunta se a retirada do carro foi no mesmo dia
                    System.out.println("\nA saída foi no mesmo dia da chegada? (1 - Sim, 2 - Não): ");
                    int mesmoDia = input.nextInt();
                    int tempoEstacionado;

                    // Se a retirada do carro for no mesmo dia
                    if (mesmoDia == 1) {
                        tempoEstacionado = tempoSaida - tempoChegada;
                        // Se o tempo for negativo no mesmo dia, o programa voltará para o Menu
                        if (tempoEstacionado < 0) {
                            System.out.println("❌ Erro: No mesmo dia, a hora de saída deve ser após a chegada.");
                            break;
                        }
                    // Se a retirada do carro for em outro dia
                    } else {
                        // Pergunta quantos dias o carro ficou estacionado
                        tempoEstacionado = tempoSaida - tempoChegada + 1440; // Um dia possui 1440 minutos
                        System.out.println("Quantos dias o carro permaneceu estacionado (ex.: 1 para o dia seguinte, 2 para dois dias, etc.): ");
                        int diasExtras = input.nextInt();
                        // Calcula o tempo com base em quantos dias passaran
                        if (diasExtras > 1) {
                            tempoEstacionado += (diasExtras - 1) * 1440;
                        }
                        // Se o tempo for negativo, programa voltará ao Menu
                        if (tempoEstacionado < 0) {
                            System.out.println("❌ Erro: Tempo inválido com os dados fornecidos.");
                            break;
                        }
                    }

                    // Calcula o valor final cobrado ao cliente
                    int horasCompletas = tempoEstacionado / 60;
                    if (tempoEstacionado % 60 != 0) {
                        horasCompletas++;
                    }
                    float valorCobrado = horasCompletas * 15;

                    // Mostra as informações finais no recibo
                    System.out.println("\n🧾 RECIBO DE PAGAMENTO 🧾");
                    System.out.println("==========================");
                    System.out.println("⏰ Chegada: " + (tempoChegada / 60) + "h " + (tempoChegada % 60) + "m");
                    System.out.println("⏳ Tempo estacionado: " + horasCompletas + "h " + (tempoEstacionado % 60) + "m");
                    System.out.println("🚗 Saída: " + (horaSaida / 60) + "h " + minutosSaida + "m");
                    System.out.println("💰 Valor a ser pago: R$ " + valorCobrado);
                    System.out.println("==========================\n");
                    lotado = false;
                    break;

                // Opção de sair do sistema
                case 4:
                    System.out.println("Saindo do sistema");
                    break;

                // Verificação de Opção Inválida
                default:
                    System.out.println("❌ Opção inválida");
                    break;
            }
        }
        input.close();
    }
}