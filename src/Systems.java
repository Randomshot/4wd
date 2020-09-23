public class Systems {
    private int days=1;
    private int maximumDays;
    private int times;
    private Commands cmd;
    private Player p1;
    private String[] blockCmd;
    private int interpretedCommand;
    private int action;
    private Interaction interaction;
    Systems(int maximumDays,int times){
        this.maximumDays = maximumDays;
        this.times= times;
    }

    public void _dayGame(){
        
        _setPlayers();
        interaction =  new Interaction(this.p1);
       while(true){
            if(_isNotFinished()){
                this.blockCmd = this.cmd._inputCommand();
                interpretedCommand = _interpretCommand();
                _excuteCommand(interpretedCommand);
                action = _checkAction();
                p1._showInventory();
                p1._showStatus();
                if(action == 1){
                    _nextDay();
                    System.out.println(this.days + "day");                    
                }
            }else{
                _newGame();
                break;
            }
        }
        
    }

    private void _newGame(){
        System.out.println("-GameOver-");
    }

    private void _setPlayers(){
        this.cmd = new Commands();
        Inventory inv = new Inventory(0,0,0,0,0,0,0,0,0);
        this.p1 = new Player(10,10,10,inv,10);
        
    }

    private boolean _isNotFinished(){
        if(this.days>=this.maximumDays) return false;
        else return true;
    }

    private void _nextDay(){
        this.days+=1;
    }

    //command 해석해서 리턴
    private int _interpretCommand(){

        if(this.blockCmd[0].equals("gathering")){
            if(this.blockCmd[1].equals("wood")) return 0;
            else if(this.blockCmd[1].equals("stone")) return 1;
            else if(this.blockCmd[1].equals("meet")) return 2;
            else if(this.blockCmd[1].equals("fruits")) return 3;
            else return -2;
        }
        else if(this.blockCmd[0].equals("make")){
            if(_checkMaterials(this.blockCmd[1]) == 1){
                if(this.blockCmd[1].equals("stoneSword")) return 10;
                else if(this.blockCmd[1].equals("stoneAxe")) return 11;
                else if(this.blockCmd[1].equals("stonePickAx")) return 12;
                else if(this.blockCmd[1].equals("ship0")) return 20;
                else if(this.blockCmd[1].equals("ship1")) return 21;
                else if(this.blockCmd[1].equals("ship2")) return 22;
                else if(this.blockCmd[1].equals("house1")) return 30;
                else if(this.blockCmd[1].equals("house2")) return 31;
                else if(this.blockCmd[1].equals("house3")) return 32;
                else return -2;
            }
            else return -3;
            
        }
        else{
            return -1;
        }

    }

    //해석된 커맨드의 리턴 값으로 실제 커맨드 실행
    private void _excuteCommand(int command){
        int currentAction = this.p1.getAction();
        String notEnoughAction = "not enough Action";
        if(command == 0){
            if(currentAction >= 1){
                System.out.println("gathering wood!!");
                interaction._gathering("wood", 100, 1, 1,2,3, 1);
                
            }
            else{
                System.out.println(notEnoughAction);
            }
        }
        else if(command == 1){
            if(currentAction >= 1){
                System.out.println("gathering stone!!");
                
                interaction._gathering("stone", 100, 1, 1, 2, 3, 1);
            }
            else{
                System.out.println(notEnoughAction);
            }
        }
        else if(command == 2){
            if(currentAction >= 1){
                System.out.println("gathering meet!!");
                interaction._gathering("meet", 100, 1, 1, 2, 3, 1);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }
        else if(command == 3){
            if(currentAction >= 1){
                System.out.println("gathering fruits");
                interaction._gathering("fruits", 100, 1, 1, 2, 3, 1);
            }
            else{
                System.out.println(notEnoughAction);
            }
        }

        else if(command == 10){
            if(currentAction >= 5){
                System.out.println("make stoneSword!!");
                interaction._making("stoneSword",30,30, 5,5,5);
            }
            else{
                System.out.println(notEnoughAction);
            }
            
        }
        else if(command == 11){
            if(currentAction >= 5){
                System.out.println("make stoneAxe!!");
                interaction._making("stoneAxe",15,15, 5,5,5);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }
        else if(command == 12){
            if(currentAction >= 5){
                System.out.println("make stonePickAx!!");
                interaction._making("stonePickAx",25,25,5,5,5);
            }
            else{
                System.out.println(notEnoughAction);
            }
        }
        else if(command == 20){
            if(currentAction >= 10){
                System.out.println("make ship0!!");
                interaction._making("sihp0", 100,100, 10 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }else if(command == 21){
            if(currentAction >= 12){
                System.out.println("make ship1!!");
                interaction._making("ship1", 150,150,12 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }else if(command == 22){
            if(currentAction >= 15){
                System.out.println("make ship2!!");
                interaction._making("ship2",200,200, 15 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }
        }
        else if(command == 30){
            if(currentAction >= 10){
                System.out.println("make house1!!");
                interaction._making("house1", 50,50,10 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }else if(command == 31){
            if(currentAction >= 11){
                System.out.println("make house2!!");
                interaction._making("house2", 75,75,11 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }else if(command == 32){
            if(currentAction >= 12){
                System.out.println("make house3!!");
                interaction._making("house3",100,100, 12 , 8, 8);
            }
            else{
                System.out.println(notEnoughAction);
            }

        }
        else if(command == -3){
            System.out.println("not enough materials");
        }
        else{
            System.out.println("error!!");
        }

    }

    //남은 행동력 계산
    private int _checkAction(){

        int currentAction = this.p1.getAction();

        if(currentAction == 0){
            this.p1.setAction(10);
            return 1;
        }
        else{
            return 0;
        }

    }

    //제작시 아이템 갯수 체크
    private int _checkMaterials(String makingItem){

        int wood = (int)(p1.getInv().getResource())[2][1];
        int stone = (int)(p1.getInv().getResource())[3][1];
        String[] itemList = new String[9];
        int i;
        int j = -1;
        itemList[0] = "stoneSword";
        itemList[1] = "stoneAxe";
        itemList[2] = "stonePickAx";
        itemList[3] = "ship0";
        itemList[4] = "ship1";
        itemList[5] = "ship2";
        itemList[6] = "house1";
        itemList[7] = "house2";
        itemList[8] = "house3";
        
        for(i=0; i<itemList.length; i++){
            if(makingItem.equals(itemList[i])) 
                j = i;
        }
       
        if(j == 0){
            if(wood >= 30 && stone >= 30) return 1;
            else return 0;
        }
        else if(j==1){
            if(wood >= 15 && stone >= 15) return 1;
            else return 0;
        }
        else if(j==2){
            if(wood >= 25 && stone >= 25) return 1;
            else return 0;
        }
        else if(j==3){
            if(wood >= 100 && stone >= 100) return 1;
            else return 0;
        }
        else if(j==4){
            if(wood >= 150 && stone >= 150) return 1;
            else return 0;
        }
        else if(j==5){
            if(wood >= 200 && stone >= 200) return 1;
            else return 0;
        }
        else if(j==6){
            if(wood >= 50 && stone >= 50) return 1;
            else return 0;
        }
        else if(j==7){
            if(wood >= 75 && stone >= 75) return 1;
            else return 0;
        }
        else if(j==8){
            if(wood >= 100 && stone >= 100) return 1;
            else return 0;
        }

        else return -1;

    }
}
