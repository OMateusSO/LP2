public class Deposito2 {
    private int items = 0;
    private final int capacidade = 10;

    //__________________________________________________________________________________________________
  
    public synchronized void retirar() {
        while (items == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida.");
            }
        }
        
        items--;
        System.out.println("Caixa retirada: Sobram " + items + " caixas");
        notifyAll();
    }

    public synchronized void retirar_balking() {
        if (items == 0) {
            System.out.println("Não há caixas disponíveis para retirada.");
            return;
        }
    
        items--;
        System.out.println("Caixa retirada: Sobram " + items + " caixas");
        notifyAll();
    }

    public synchronized void retirar_guarded_suspension() {
        while (items == 0) {
            try {
                System.out.println("Não há caixas disponíveis para retirada. Aguardando...");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida.");
            }
        }
        
        items--;
        System.out.println("Caixa retirada: Sobram " + items + " caixas");
        notifyAll();
    }

    public synchronized void retirar_timed_wait(long milliseconds) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        long remainingTime = milliseconds;
        
        while (items == 0) {
            if (remainingTime <= 0) {
                throw new InterruptedException("Timeout!");
            }
            wait(remainingTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            remainingTime = milliseconds - elapsedTime;
        }
        
        items--;
        System.out.println("Caixa retirada: Sobram " + items + " caixas");
        notifyAll();
    }
    
  
    //__________________________________________________________________________________________________
    public synchronized void colocar() {
        while (items == capacidade) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida.");
            }
        }
        
        items++;
        System.out.println("Caixa armazenada: Passaram a ser " + items + " caixas");
        notifyAll();
    }
  
    //__________________________________________________________________________________________________
    public static void main(String[] args) {
        Deposito2 dep = new Deposito2();
        Produtor p = new Produtor(dep, 2);
        Consumidor c1 = new Consumidor(dep, 1, "retirar");
        Consumidor c2 = new Consumidor(dep, 1, "retirar_balking");
        Consumidor c3 = new Consumidor(dep, 1, "retirar_guarded_suspension");
    
        // Inicia as threads
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
    
        System.out.println("Execucao do main da classe Deposito terminada");
    }

    //__________________________________________________________________________________________________
    public static class Produtor implements Runnable {
        private final Deposito2 dep;
        private final int tempoProducao;

        public Produtor(Deposito2 dep, int tempoProducao) {
            this.dep = dep;
            this.tempoProducao = tempoProducao;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(tempoProducao * 1000);
                    dep.colocar();
                }
            } catch (InterruptedException e) {
                System.out.println("Produtor interrompido.");
            }
        }
    }

    //__________________________________________________________________________________________________
    public static class Consumidor implements Runnable {
        private final Deposito2 dep;
        private final int tempoConsumo;
        private final String metodo;
    
        public Consumidor(Deposito2 dep, int tempoConsumo, String metodo) {
            this.dep = dep;
            this.tempoConsumo = tempoConsumo;
            this.metodo = metodo;
        }
    
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(tempoConsumo * 1000);
    
                    switch (metodo) {
                        case "retirar":
                            dep.retirar();
                            break;
                        case "retirar_balking":
                            dep.retirar_balking();
                            break;
                        case "retirar_guarded_suspension":
                            dep.retirar_guarded_suspension();
                            break;
                        case "retirar_timed_wait":
                            dep.retirar_timed_wait(1000);
                            break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumidor interrompido.");
            }
        }
    }
}
