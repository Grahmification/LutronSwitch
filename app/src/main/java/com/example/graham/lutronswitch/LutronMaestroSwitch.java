package com.example.graham.lutronswitch;


public class LutronMaestroSwitch implements iIRDevice<LutronMaestroSwitch.Commands>{

    @Override
    public int Frequency() {
        return 40000;
    }

    @Override
    public String Function(Commands cmd) {
        switch (cmd){
            case FullOn: return "0000 0069 0006 0000 032a 010e 005a 010e 005a 0168 005a 00b4 005a 005a 010e 0276";
            case FullOff: return "0000 0069 0007 0007 032a 010e 005a 010e 005a 005a 0168 005a 005a 010e 005a 00b4 005a 01c2 032a 010e 005a 010e 005a 005a 0168 005a 005a 010e 005a 00b4 005a 01c2";
            case Raise: return "0000 0069 0006 0006 032a 010e 005a 010e 005a 01c2 005a 010e 005a 010e 005a 01c2 032a 010e 005a 010e 005a 01c2 005a 010e 005a 010e 005a 01c2";
            case Lower: return "0000 0069 0006 0006 032a 010e 005a 010e 005a 01c2 005a 0168 005a 005a 005a 021c 032a 010e 005a 010e 005a 01c2 005a 0168 005a 005a 005a 021c";
            case FavoriteScene: return "0000 0069 0007 0007 032a 010e 005a 010e 005a 0168 005a 00b4 005a 005a 00b4 005a 010e 0168 032a 010e 005a 010e 005a 0168 005a 00b4 005a 005a 00b4 005a 010e 0168";
            default: return "";
        }
    }

    enum Commands {FullOn, FullOff, FavoriteScene, Lower, Raise}
}

