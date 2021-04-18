public static void main(String[] args) {

        String IP="";
        String port="";
        String proxy = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Java\\text.txt"));
            String Line;//переменная для считывания файла
            FileWriter writer = new FileWriter("C:\\Java\\good.txt");
            while ((Line = br.readLine()) != null) {
                //System.out.println(Line);
                String []  newLine = Line.split("\\s+");
                for (int i = 0; i <newLine.length ; i++) {
                    newLine[i]= newLine[i].replaceAll("[^\\w]",".");

                    IP = newLine[0];
                    port = newLine[1];
                    //checkPpoxy(IP, Integer.parseInt(port));
                    String finalIP = IP;
                    String finalPort = port;
                     //1 способ
                    MyThread thread = new MyThread();

                    thread.finalIP  =IP;
                    thread.finalPort = port;
                    thread.start();
                    //2 способ
                    Thread thread_1 =new Thread(new MyThread_2(port,IP));

                    // 3 способ
                    /*Thread threed= new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String result = checkProxy(finalIP, Integer.parseInt(finalPort));
                            if (result.equals("error"));
                            else{
                                System.out.println(finalIP+" : "+finalPort );
                                try {
                                    writer.write(finalIP+" : "+finalPort+"\n");
                                writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    threed.start();*/
                } //System.out.println(" IP: "+ IP + "  " +" port: "+ port);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        //1 способ
        static class MyThread extends Thread{
            String finalIP ;
            String finalPort;
            @Override
            public void run(){
                String result = checkProxy(finalIP, Integer.parseInt(finalPort));
                if (result.equals("error"));

                else{
                    System.out.println(finalIP+" : "+finalPort +"\n");
            }
        }}
        //2 способ
        static class MyThread_2 implements Runnable{
        String result;
        String finalIP;
        String finalPort;

            public MyThread_2(String finalIP, String finalPort) {
                this.finalIP = finalIP;
                this.finalPort = finalPort;
            }

            @Override
            public void run() {
                String result = checkProxy(finalIP, Integer.parseInt(finalPort));
                if (result.equals("error"));

                else{
                    System.out.println(finalIP+" : "+finalPort +"\n");
                }
            }
        }
        static String  checkProxy (String IP, int port){
        HttpURLConnection connection = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(IP, port));
            URL url = new URL("https://vozhzhaev.ru/test.php");
            connection = (HttpURLConnection) url.openConnection(proxy);
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
           return response.toString();
            //System.out.println(response.toString()+ IP +":"+port+ " - работает прокси");

        } catch (Exception e) {
            return "error";
            //System.out.println(IP+":"+port +" Этот прокси не работает");;
        } finally {
            if (connection !=null){
                connection.disconnect();
            }
        }}}
