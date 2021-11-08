package com.example.morpion.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private com.example.morpion.db.AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        //appDatabase = Room.databaseBuilder(context, com.example.ecoledesloustics.db.AppDatabase.class, "ecoledesloustics").build();

        ////////// REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "morpiondb").addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public com.example.morpion.db.AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //default player
            db.execSQL("INSERT INTO player (name, picture, wins, draws, defeats) VALUES(\"IA\", " +
                    "\"iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAYAAAD0eNT6AAAAAXNSR0IArs4c6QAAAARzQklUCAgI\n" +
                    "CHwIZIgAACAASURBVHic7d13vB1Vvffxz0kFEnpPQHq1IFJVECIWigRURNQH9aKP+ohevfaGVxHF\n" +
                    "a3m8XFEfr4r1SrFQBFQ6oiBSBQVBUAQSQpWaQEKS5491IsfAOWeXmf2bNevzfr3W60T/2PvL7Dl7\n" +
                    "vmfNzJohJLXRELAxMGN4rD/i3yP/vynAHcDc5cbI/+8vwKKBppckSR2bAuwNfA2YAyytaDwAnAC8\n" +
                    "Blh1YP81kiRpVKsDrwNOAh6kuoP+aGMhcBZwOLDBAP77JEnSsAnAG4BzSVPzdR/0xxqXA+8HVqr1\n" +
                    "v1iSpMLtB1xL7EH/qcZc4H8DE+v7T5ckqTw7AxcQf6Afb1wHHFDPJpAkqRybk87vRx/Yux0XAc+t\n" +
                    "YXtIktRq04Avky66iz6Y9zN+ihcLSpLUkY2A3xN/8K5q3AHsWukWkiSpZV4A3EX8Qbvq8ShwaIXb\n" +
                    "SZKk1ngL+U/5jzc+R7qNUZKk4k0CjiX+4DyocTqwSiVbTpKkTK1BWtAn+qA86PFHYLMKtp8kSdmZ\n" +
                    "BlxN/ME4atyBdwhIkgozBJxM/EE4elyBywhLkgryaeIPvk0ZJ/a5LSVJysJriT/oNm0c0dcWlSSp\n" +
                    "4XYGFhB/wG3aWAK8oo/tKklSY80kPTUv+mDb1PEwsF3PW1eSpAaaDFxO/EG26eMWYPXeNrGkbvjs\n" +
                    "bmkw3gEcFh0iA6uRytJZ0UGkthuKDiAVYBXgZmCt6CCZeAzYCvhbdBCpzVyTW6rfh/Dg342pwFHR\n" +
                    "ISRJ6scGwHziz63nNpYAz+5he0uS1AjHEX8wzXX8softLUlSuGcCi4k/kOY8XtT1VpckKdiZxB9A\n" +
                    "cx9X4sXKkqSMbE/8wbMtY58ut72kDngXgFQPl7WtjttSqoFTa1I9riFdA6D+3QnMIN0ZIKkizgBI\n" +
                    "1dsED/5VWhfYJTqE1DYWAKl6B0QHaCG3qVQxC4BUvQOjA7SQ21SqmNcASNVak3TO2gdtVW9r4Ibo\n" +
                    "EFJbOAMgVWs/PPjXxdMAUoUsAFK1XhYdoMXctlKFLABStTaPDtBiblupQhYAqVozogO02Lp4ekWq\n" +
                    "jAVAqs4kYO3oEC02gVQCJFXAAiBVZz38naqbMyxSRfyykqrjwal+bmOpIhYAqTrrRwcogNtYqogF\n" +
                    "QKqOf53Wz20sVcQCIFXHg1P93MZSRSwAUnW8Qr1+bmOpIhYAqTrzowMUwG0sVcQCIFXn79EBCuA2\n" +
                    "lipiAZCq48Gpfm5jqSIWAKk6Hpzq5zaWKmIBkKrjwal+bmOpIhYAqTr3RgcogNtYqogFQKrOdcDS\n" +
                    "6BAt94foAFJbWACk6jwA/Dk6RIs9ANwUHUJqCwuAVK3LowO02BU4wyJVxgIgVcsCUB+3rVQhC4BU\n" +
                    "LQ9S9XHbShUaig4gtcw04D5gSnSQFtoIuDU6hNQWzgBI1XoE+GV0iBb6HR78pUpZAKTqnRAdoIV+\n" +
                    "GB1AahtPAUjVmw7cCawUHaQllgAzgXnRQaQ2cQZAqt7DwOnRIVrkfDz4S5WzAEj18DRAdZz+l2rg\n" +
                    "KQCpHlNIqwI+LTpI5u4BNiHNqkiqkDMAUj0WAkdHh2iBL+LBX6qFMwBSfZwF6I9//Us1cgZAqo+z\n" +
                    "AP3xr3+pRs4ASPVyFqA3/vUv1cwZAKleC4GPRIfI0L/jwV+S1AInkx5l6xh/nI2zk5KkllgHuJv4\n" +
                    "g2vTx/3Ahj1uY0mSGukg4g+wTR+v73nrSpLUYD8k/iDb1HFyH9tVkqRGWwW4mviDbdPGH4HV+9iu\n" +
                    "kiQ13gzSs+2jD7pNGXPxNklJUiGeDvyd+INv9HgQeHaf21KSpKzsATxK/EE4aiwEXtz3VpQkKUOz\n" +
                    "gfnEH4wHPR4l3RUhSVKxdqWsNQLuBXarZMtJkpS5zUnPDIg+ONc9bgK2rGibSZLUCmsBlxB/kK5r\n" +
                    "XAKsXdnWkiSpRVYAPgcsJv6AXdVYPPzftEKF20mSpFbaFbie+IN3v+P64f8WSZLUoZxnA/yrX5Kk\n" +
                    "Pn2M+AN6t+NjtWwJSZWZEB1A0rgejg7QgxwzS0WxAEiSVCALgCRJBbIASJJUIAuAJEkFsgBIklQg\n" +
                    "C4AkSQWyAEiSVCALgCRJBbIASJJUIAuAJEkFsgBIklQgC4AkSQWyAEiSVCALgCRJBbIASJJUIAuA\n" +
                    "JEkFsgBIklQgC4AkSQWyAEiSVCALgCRJBbIASJJUIAuAJEkFsgBIklQgC4AkSQWyAEiSVCALgCRJ\n" +
                    "BbIASJJUIAuAJEkFsgBIklQgC4AkSQWyAEiSVCALgCRJBbIASJJUIAuAJEkFsgBIklQgC4AkSQWy\n" +
                    "AEiSVCALgCRJBbIASJJUIAuAJEkFsgBIklQgC4AkSQWyAEiSVCALgCRJBbIASJJUIAuAJEkFsgBI\n" +
                    "klQgC4AkSQWaFB1AjTMVGIoOoX+S4+/pJGCF6BD6J0uBhcM/Jb/oCzEV2ALYasTYAFgZmD78c9m/\n" +
                    "JwZllFS/JcAjwEPAw8M/HwLuAm4A/jT888bh/18tZgFop22AWcNjB2AjPN0jqTtzgWuAC4DzgSuA\n" +
                    "xZGBVC0LQDusD+xPOuDvCawXmkZSGz0EXEQqA2cC18XGUb8sAPlaCXg58HpgL5y6lzRYVwHfB44H\n" +
                    "5gVnUQ8sAPmZBbwReAXpnL0kRVoMnE0qAz8mXWioDFgA8jAEHAh8lHROX5KaaA7wReC/SRcbqsEs\n" +
                    "AM02EXgN8GFg2+AsktSpe4FjgC8D9wdn0SgsAM11MHA0sGl0EEnq0UPAl4DPAguCs2g5FoDm2QI4\n" +
                    "FnhJdBBJqshfgXcCZ0QH0RO8N7w5VgA+CVyLB39J7bIJcDpwCmldEjWAt441w+7AWaTb+nJc9lWS\n" +
                    "OrE18BbgMeC3wVmK5ymAWEOkC/yOxDImqSy/IK1jcnd0kFJZAOKsDfwAp/sllesO4HWk1QU1YP7V\n" +
                    "GWMP0sIZz44OIkmBVgYOJR2LLsInFQ6UMwCD9y+kRTI81y9JTziDdPvz/OggpfAugMH6EHAcHvwl\n" +
                    "aXn7AecBa0UHKYUzAIMxBPxf4N3RQSSp4W4E9iatHaAaWQDqNxn4DvDa4BySlIt5wL6kJw6qJhaA\n" +
                    "ek0gPSrz4OggkpSZ+4EXkBZHUw28BqBex+DBX5J6sRpprYCNg3O0lgWgPh8F3hEdQpIyNoO0Suo6\n" +
                    "0UHayFMA9Xgz8I3oEJLUElcAs0hPF1RFLADVewlwJi6yJElVOgvYB1gSHaQtPEhVa33SCn/To4NI\n" +
                    "UstsRlop8MLoIG3hDEB1JgDnAnsG55CktloCvBQ4JzpIG3gRYHU+jgd/SarTBOB/SLOt6pMzANWY\n" +
                    "RWqkFipJqt+vgBcCi6OD5MxrAPo3jXTwXy06iCQVYiPSQ4N+Ex0kZ/7F2r8jgA2jQ0hSYT5OKgLq\n" +
                    "kacA+rM1cA1pvX9J0mCdBhwQHSJXngLoz/HAFtEhJKlQW5EeGHRDdJAcOQPQu4OBE6NDSFLhbgW2\n" +
                    "IV0ToC44A9CbycDP8MI/SYq2KrAQFwjqmhcB9uZQvPhEkpriXbgCa9ecAejeROAEYM3oIJIkAFYE\n" +
                    "7gcujg6SE68B6N5rgB9Gh5Ak/ZM7gY2BR4NzZMMZgO4MkQ7+PptakpplOqkE/C46SC6cAejO/qT7\n" +
                    "TiVJzXMbsAkuEdwRLwLszmHRASRJo9qQ9LRAdcAC0Lk1gH2jQ0iSxvTG6AC5sAB07hBgSnQISdKY\n" +
                    "ZgOrR4fIgQWgc4dGB5AkjWsq6W4tjcOLADuzBXBjdAhJUkcuA3aODtF0zgB05qDoAJKkju1EuhtA\n" +
                    "Y7AAdGZWdABJUlf2ig7QdBaA8U0Bnh8dQpLUlRdGB2g6C8D4dgZWig4hSeqKM7fjsACMb8/oAJKk\n" +
                    "rq0HbBsdosksAOOzRUpSnjwNMAYLwNiGgF2iQ0iSevLc6ABNZgEY20xgWnQISVJPtooO0GQWgLG5\n" +
                    "80hSvvwOH4MFYGxbRweQJPVsOmkmV0/BAjA226Mk5c3v8VFYAMbmjiNJefN7fBQWgLFtHB1AktSX\n" +
                    "jaMDNJUFYGyrRAeQJPXF7/FRWADGtnJ0AElSX/weH4UFYHQT8BkAkpS76dEBmsoCMLpppJUAJUn5\n" +
                    "cgZgFBaA0bnTSFL+/C4fhQVgdC4BLEn587t8FBaA0U2MDiBJ6pvf5aOwAEiSVCALgCRJBZoUHUBF\n" +
                    "uR+YC9wxPJb9+z5gLWD95cYMYNWQpFI1HuaJ/X3kfn8X6eK0Zfv5yH1+TbwDSQNgAVBdFgDnAKcC\n" +
                    "FwBzgEd7eJ0VSU/zeiFwALAXMLWaiFKlFgO/Ie3zvwRuBR7q4XUmA+sBzwUOBPYBVqsoo/QPtszR\n" +
                    "bQ1cHx0iM/cAp5O+AM8C5tfwHtOBvUllYD9g9RreQ+rUfOBs4BTSvn9PDe8xGdiDtM/PBp5Ww3u0\n" +
                    "2Q34aHd1aWtgqWPc8RjwVWB3Bn+17STSzMC3gMd7yO5w9DpOIR2MV2Twtgc+DTzQRd6Sx59628wq\n" +
                    "mQVg7LEEOB7YtNcNXLGtSV/K0dvF0e7xG+D5NMNawDHAQuK3S5OHBUBdswCMPs4Ddux909ZqN+Bi\n" +
                    "4reRo13jT8DLaabNgBNJpTx6OzVxWADUNQvAk8e1wL79bNQBeiXp3F/0NnPkPeYBbyOPC6Z3As4n\n" +
                    "fps1bVgA1DULwBNjEfAu8ls3YhLwUdLV2dHb0JHf+Dp5PknulXh9wMhhAVDXLABp3Ee69S5ns0m3\n" +
                    "Y0VvS0ceYxFwOHnbFriJ+G3ZhGEBUNcsAOk2yM373ZAN8Uzgr8RvU0ezx73kX3iXWYN0vU70No0e\n" +
                    "FoBR5Dalq8H5JbAr6a+INrgW2Bm4KDqIGut6YBfg3OggFbkPeAnpNl1JXSh5BuBLtPcJWlOAbxK/\n" +
                    "jR3NGmcAq9Be/4d0aiN6O0cMZwDUtVILwPuq2HgZOIr4be1oxvgeZcyG7kOZC2ZZANS1EgvANyrZ\n" +
                    "cnkYAk4ifps7YsfFlPVsiXcSv80HPSwA6lppBeBC0prjJVkJuIL4be+IGbcC61Ke/yZ+2w9yWABG\n" +
                    "UcK0l8b3V9K9w4uigwzYfNIDVuZFB9HALfvs74wOEuBwvBhWWACU7o+fTT1PMcvB7aRHrvbyqGLl\n" +
                    "aSnwRuCq4BxRFpEK/y3BORQsh+UtuzWRNK03A1iT3h95vEFliZprCfA64A/RQYJdCrwZ+EF0EA3E\n" +
                    "p4AfRYcIdjdpBuQ35LnaYTemkR4h3oulpFUV5wJ30LJZ0l4Pjk2w7EOdRTpYzyQd9NelvbewVe1o\n" +
                    "4CPRIRrkq6TbpdRe5wIvJn2xC14PfDc6RCaWkorTXGDO8M+LgZ+RFpDKTm4FYC3SdPWBpF/iFWLj\n" +
                    "ZO0u0ip/D0UHaZC1gZuBlaODqBZLgecAV0cHaZAh4HLSdlFvFgO/Ak4mPZL8ttg47TIROIx0lXqJ\n" +
                    "97DWNXJf67wuRxD/2TjqGd9HT2Uv4j+bNo3LgH/DP1D7tjdpCdfoD7Rt40bKu+WvU9NI5/qiPyNH\n" +
                    "teNRYCM0mp8T/xm1bdwCvJb8ZtrDPQs4i/gPsK3joM4/iiK9jfjPyFHt+AIay7Pwsdl1jcuAF3T+\n" +
                    "UZRrXeA43BHrHJd0/GmUaxJwA/GflaOacR+wOhrPd4j/rNo8TgE26/TDKM0OpIsnoj+kto/dO/1A\n" +
                    "CncQ8Z+Vo5rxftSJDYEFxH9ebR5/Jz2hUSMcTFqZK/rDafu4sNMPREB6PGz0Z+bob9yPF2N14+vE\n" +
                    "f2ZtH4+TnskQLnolwCHgSOBEYMXgLCX4aXSAzJwcHUB9+zmu8tgNvyPqNxH4L1LZKvZi7GnAT4hv\n" +
                    "YyWNjTv5YPQPOxP/mTn6G4c86VPVWKYADxL/uZUyzietWFuUScB5xG/8ksbvO/pkNNIQ6VkB0Z+d\n" +
                    "o7exEFj1SZ+qxnMi8Z9dSeMq0pNJBy7qFMAxpCV8NTinRgfI0FLcbjm7gLSOu7rjPj9Yzybd/TZw\n" +
                    "EQXgrcDbA963dP5S9+aU6ADqmft8b86kZQ+9ycCrgQ8N+k0HvULRHsDZFHzhQ5DbSbf4qHuTSQ8A\n" +
                    "cSo5PxuS9n117xzSEsEanCXAy0gXrg7EIGcANgZ+jAf/CKdFB8jYIuCM6BDq2pV48O+HsyeDNwE4\n" +
                    "HthykG84CEPASaSn+WnwrogOkDm3X378zPrj9ouxKulYOZBj86AKwKuBnQb0XnqyO6IDZM7tlx8/\n" +
                    "s/7MiQ5QsO2A1w/ijQZRACYDRw3gfTS6udEBMuf2y4+fWX+WPRFTMY5kACtYDqIAvBUfgBDNv4b6\n" +
                    "4/bLj59ZfxYC90aHKNiGwDvqfpO6C8B04Iia30Nje5x0Fbt658EkP35m/fM0QKwPA6vV+QZ1F4D3\n" +
                    "AevU/B4a2504ldevh4BHokOoKxaA/nkaJdYa1Lw2QJ0FYCrwnhpfX53xl7gabsd8LAXmRYdoAff5\n" +
                    "eP9Kem5OLeosAC8CVq7x9dUZ/xKqhtsxH3eTTn2pPxaAeCsCL6nrxessAAfU+Nrq3ILoAC3hdsyH\n" +
                    "n1U13I7NMLuuF66rAAwB+9f02urO+tEBWsLtmI/1ogO0hPt8M+xHTcfqugrALvhL2BT+ElfD7ZiP\n" +
                    "qaQLqNSfGdEBBMDawHPreOG6CsCBNb2uuueBq3+TcRnr3Ljf988C0By1nAaoqwDUds5CXZs+PNS7\n" +
                    "dRn8kzPVHwtA/ywAzVHLKfU6CsAkYKsaXle988uwP26//PiZ9WcIt2GTbEU6tlaqjgKwXk2vq97Z\n" +
                    "5Pvj9suPn1l/1gKmRIfQP0yghuvq6jhQ+4vXPDb5/rj98uNn1h+/x5un8s/EAlCGTaMDZM7tlx8/\n" +
                    "s/74ALfmyaIAzKzhNdWffaMDZG6/6ADq2gsZwONUW8zvjObJogA4A9A8z8WHMvVqC2Db6BDq2jRg\n" +
                    "r+gQmZqAC7k1kQVAPfEXuncvjw6gnrkeSW/8g6GZsigAK9bwmuqfz2bojQeRfO2PdyT1wu+KZlqp\n" +
                    "6hf0l6McL6aGHajl1gd2jQ6hnq2Ln18vLL2FsACUYwXgpdEhMjMbVwDMnX/Ndmcb0nUvKoAFoCx+\n" +
                    "GXbH8//5c5/vjturIBaAsrwCL+7p1BZ4FXkbbAXsGR0iE5OBN0WH0OBYAMqyMvDx6BCZ+DQ1rL2t\n" +
                    "EJ/HUzmdeCuweXQIDY4FoDxvwV/y8ewEvCo6hCqzI3BwdIiG84+DAlkAyjMZODo6RMN9LjqAKvcZ\n" +
                    "fLjNWD4ArB0dQoNlASjTQcAu0SEaah88Z9xGmwJviw7RUOsD74kOocGzAJTr89EBGmgC8NnoEKrN\n" +
                    "EcAq0SEa6JO4RkiRLADl2h2XB17e/wKeFR1CtVkL+GB0iIbZBjgsOoRiWADK9lXSammCjYEvRIdQ\n" +
                    "7d5LWuteaXGw7wITo4MohgWgbBsAJwNTo4MEmw6cihdBlWAq8FPSvl+6b5DueFGhLAB6LvD16BCB\n" +
                    "hoDv49R/SdYDTqHsB5e9n3TKSwWzAAjgDcD7okME+RQ+/KREOwDHRYcIsi9e7CosAHrCf5C+GEpy\n" +
                    "CPDR6BAKcwjw4egQA7Y1cDx+9wt3Aj1hAumLYZvoIAOyI+X+BagnHEU5d8OsBpyGt0JqmAVAI60C\n" +
                    "nAU8JzpIzXYDzqTsc8BKlhXfV0QHqdlM4Gx81K9GsABoeRsAv6a9a6e/CTgXr/jXE6YBPwb+nXY+\n" +
                    "NGhX4HLSrJf0DxYAPZUVgRNJF8i15QtxIvCfwDdxTXg92RDwCeBHpELQFm8ALiDd+SD9EwuAxvIx\n" +
                    "4Cfk/4W4GmnK/13RQdR4rwR+A2wUHaRPE4EvAt/BdT40CguAxvNy4GLy/ULcCrgUeEl0EGVjO+Ay\n" +
                    "0nLZOVoNOAMf8KNxWADUiWcBV5MWD1khOEunppGeb345sGVwFuVnbeA84FhgneAsnZoAvBG4Fnhp\n" +
                    "bBTlwAKgTq0GfA64EXg9zd13JgFvBW4iPeVsemwcZWwScDhpXzqCZp8K24dU0r+NyxyrQ039Eldz\n" +
                    "bUh6gMhVNO+vjANJf/38P7zoSdVZGTgS+DPwFpr18JwdSHe1nAk8MziLMmMBUK+eBfwCOAfYOTjL\n" +
                    "7qRbF08mrXQm1WF90nMzriWVzcgisAXwQ9K1Ci8MzKGMTYoOoOztRbrI7i+kJ+qdSjoYL67xPScB\n" +
                    "e5C+hGcDT6vxvaTlbUMqm/cAp5P2+bOA+TW/7w7AAcPDh1epb3Xc430C8OoaXlf5uJd0FfKpwC+B\n" +
                    "Ryp4zZWBvUlffvuRrkmQmmIBaaW9U0il4O4KXnMyMIu0z8/Gc/ulO5H0/IrKWABUt0dJV+LPAe4A\n" +
                    "5g7/HPnv+4E1SFOsI8eM4Z8zSauYeT+zcrAEuBK4lSfv88v+9z2kiwpH7ufL/3tHXLdfT6i8AHgK\n" +
                    "QHVbgbT2/liW4PUoao8JpIP3WEvvus8rnDugmsD9UKVxn1c4d0JJkgpkAZAkqUAWAEmSCmQBkCSp\n" +
                    "QBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAW\n" +
                    "AEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJ\n" +
                    "kgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIK\n" +
                    "ZAGQJKlAFgBJkgpkAZAkqUAWAEmSCjQpOoDUQA8CdwyPu4ElsXHUhzWA9YfHmsFZpEaxAKhk84Gz\n" +
                    "gZ8BN/DEQX9+ZCjVZgqwHqkMbALsA7yMVBKk4lgAVJq7gNOBU0kH/wWxcTRAC4Fbh8elwAnARGB3\n" +
                    "4IDhsUlYOmnAvAZApfgVMIv019+bgNPw4C9YDFwA/BuwKbA98OPIQNKg1FEAltbwmlKvriVN8+5B\n" +
                    "+qL3fL7GcjXwKmAX0v4iNUXlx9Y6CsCDNbym1K1bgTcAzwbOCM6i/PyONGO0L/D74CwSwENVv2Ad\n" +
                    "BWBuDa8pdeNoYEvge/gXv/rzc+A5wNtJ1xBIUeZU/YJ1FIDKQ0odWgC8BvgI8FhwFrXHEuBrwF6k\n" +
                    "i0ilCJX/ce0MgNridtLV3CdEB1Fr/RrYGU8JKIYFQHoKvwV2Aq6IDqLW+xvwfOAn0UFUnCxOAVgA\n" +
                    "NEhnAnsC84JzqByPkO4UODY6iIpS+bF1qOoXHH7Nx4DJNby2NNJ1wK7UcHWs1IEJpEWl9okOotZb\n" +
                    "BEyl4lsB61oH4LIaXlca6T5gNh78FWcJcAhwfXQQtd7lZLIOAKRlVqW6PE6agr05OoiK9yCwP6mQ\n" +
                    "SnU5rY4XrasAnFLT60oA7wbOiw4hDbsZOIhUTKU6ZFUAbsRpMdXjJOAr0SGk5ZwPfDw6hFrpZtL1\n" +
                    "TpWr82FAngZQ1R4DPhgdQhrFl4DbokOodWr56x/qLQCeBlDVvgLcEh1CGsWjOAug6tVWAOq4DXDk\n" +
                    "a/8F2LjG91A57gc2w4ut1GwTSCsFPiM6iFphHrAhNV1fUucMwFJsw6rOZ/Dgr+ZbAnwoOoRa40hq\n" +
                    "vLi0zhkASAXjSmC7mt9H7XYrsBVpilXKwQXAHtEhlLU/A9tSYwGocwYAUhv2oi3165t48FdevFNF\n" +
                    "/fowNd9aWvcMwDLnkB6lKfViO+Ca6BBSF1YG7gGmRAdRli4lLXNeq7pnAJb5IDUsY6gi/BUP/srP\n" +
                    "Q7hYlXr3gUG8yaAKwBXAtwb0XmoX15NQrrwVWr04CfjVIN5oUKcAID3J6AIGMK2hVplF2m+k3KxP\n" +
                    "eob7IL9nlbdrgecBDw/izQa9Y65HelLgBgN+X+XpPmAdYHF0EKlHlwI7R4dQFu4BdmKAi50N6hTA\n" +
                    "MvOAA4AFA35f5enXePBX3i6IDqAsLAJeyYBXOh10AYC0LsAbA95X+ZkTHUDqk/uwOnE4AzrvP1JE\n" +
                    "AYB0kcPHgt5b+ZgbHUDqk/uwxvMF4BsRbxxVAAA+TZoJeCwwg5rNL0/lzn1Yo1kMvBt4f1SAyAIA\n" +
                    "8F3SVd53BudQMzl9qty5D+upPAi8DDgmMkR0AQC4hHTl41XRQdQ4/vWk3N2Bi6Dpn/2FdDv8L6KD\n" +
                    "NKEAANwG7Ab8KDqIGsUCoNwtBO6NDqHGuJB0W+j10UGgOQUAYD5wMLA/8MfgLGqGidEBpAq4H+s2\n" +
                    "4A3AC2lQIWxSAVjmdNLDX96M589KNyM6gNSnFYHVo0MozIOkp/ptCXyP9ITcxmhiAYB0deS3gC2A\n" +
                    "j5I2ospjAVDu3IfLtAj4MrAZ8Fka+jjzphaAZRYAnwE2Bg4DTqOhG1K18MtTuXMfLsdi4CLgvcDm\n" +
                    "wL+SlvdtrEnRATr0d+Dbw2Ma8FLg5cB+OL3WZn55Knfuw+22ADib9OTHn9HwA/7ycikAIz0C/HR4\n" +
                    "TAKeTXq40EzSL9uyMRNYk94feDQRWKvfsOrLzOgAUp8sAPEW0/uBeSnwAOmOpDnDP0f++2rSBexZ\n" +
                    "yrEAjPQ4cPnwqNrWNORWjYL51Ejl7mnRAcRNpO9zLafp1wCobLsBU6JDSH3YKzqANBoLgJpsFdJS\n" +
                    "0VKONgGeGR1CGo0FQE13YHQAqUcHRAeQxmIBUNPNpvcLOaVIFgA1mgVATTcD2DE6hNSlNYDdo0NI\n" +
                    "Y7EAKAeeBlBu9sNnAKjhLADKwZuBlaNDSB0aAt4dHUIajwVAOVgHeH90CKlDrwGeEx1CGo8FQLl4\n" +
                    "L7B+dAhpHFOBT0eHkDphAVAuVgI+GR1CGsfhpIeXSY1nAVBODgO2jQ4hjWI10uPLpSxYAJSTicAX\n" +
                    "cV0ANdMnSLf/SVmwACg3ewNHRIeQlvNq4F3RIaRuWACUo08Ar4gOIQ3bAfh2dAipWxYA5WgI+B6w\n" +
                    "XXQQFW894BRgxeggUrcsAMrVNOBUYO3oICrWVOBkYIPoIFIvLADK2Uakv75WjQ6i4kwGvgPsGpxD\n" +
                    "6pkFQLl7HvBbYIvoICrGWsDZwCHRQaR+WADUBlsDlwIvjg6i1nsmcBmwR3QQqV8WALXF6sDP8VYs\n" +
                    "1edA4GJc6U8tYQEY3ePRAdS1icB/AseRCoFUhRVIy1D/FJgenEXd87t8FBaA0T0UHUA9+xfgL8AH\n" +
                    "8PYs9W4CaV+6Efg4rkCZK7/LR2EBGN3D0QHUl9WA/yB9eb+JNDsgdWp/4BrSbNKGwVnUH7/L30OB\n" +
                    "ZwAACKdJREFUR2EBGN0jwJLoEOrbBsA3SV/mh+AUrkY3hbTU9EXAacDTY+OoIs4AjGJSdICGexhY\n" +
                    "JTqEKrEtcDzwGHAuaRGh04B5kaEUblVgH+AAYF/8fW8jC8AoPKc1tjnAjOgQqs1S4HfAz4A/AXOH\n" +
                    "xx3AwsBcqt5EYF3S7/MMYFPSgX8WaVEftddXgcOjQzSRMwBjux8LQJsNAbsMj5GWAveQysBdeCoo\n" +
                    "V0PAmqTf4XXwOpBS3R8doKksAGO7iTR1rLIMkZ4x4HMGpPzdFB2gqbwIcGw3RAeQJPXF7/FRWADG\n" +
                    "5o4jSXnze3wUFoCx/Sk6gCSpZ/cA90aHaCoLwNhsjpKUL7/Dx2ABGNs9w0OSlJ/rowM0mQVgfL+O\n" +
                    "DiBJ6smvogM0mQVgfOdHB5Ak9eS86ABNZgEYnwVAkvJzI2k1V43CAjC+PwB3R4eQJHXl3OgATWcB\n" +
                    "GN9S4MLoEJKkrjj9Pw4LQGfOjg4gSerY43j6dlwWgM78BFgUHUKS1JFf4gJA47IAdOZe4MzoEJKk\n" +
                    "jnw3OkAOLACd+150AEnSuO4HTosOkQMLQOfOAP4eHUKSNKYTgMeiQ+TAAtC5x4CTokNIksbkbG2H\n" +
                    "hqIDZGZ74MroEJKkp3Q16XtaHXAGoDtX4cWAktRUR0UHyIkzAN17HvCb6BCSpH9yHfAM0uJt6oAz\n" +
                    "AN27GBeYkKSmOQoP/l1xBqA3ewHnRIeQJAHpwT/bAEuig+TEGYDenIunASSpKT6FB/+uOQPQu+2B\n" +
                    "y4CJ0UEkqWC/Bl6A0/9d8+DVu3nA2sDO0UEkqVCPA/sDd0UHyZGnAPpzBO54khTlS8AfokPkyhmA\n" +
                    "/jxKKgAvjw4iSYW5DXgVPqm1Z84A9O97wAXRISSpMO8AHokOkTMLQDUOxWdPS9KgHItP/OubdwFU\n" +
                    "52WkHdJtKkn1uQzYDVgYHSR3XgNQnRuBlUlLBUuSqncf8KLhn+qTf61WazLpnlRvDZSkai0l3fJ3\n" +
                    "RnSQtvAagGotAg4mrREgSarOJ/HgXylnAOqxHXAhsGp0EElqga8Bb48O0TYWgPrsAfwCWCE6iCRl\n" +
                    "7ETgtbjWf+UsAPU6EPgxXmwpSb04i3SHlYv91MADU73+BNwOzMayJUnduIR08H80OkhbWQDqdxXp\n" +
                    "FsHZuL0lqRNnkr4zXemvRt4FMBjHA/sBD0cHkaSGOw44AA/+tbMADM7ZwCzg7uggktRQRwFvIj3m\n" +
                    "VzXzvPTgbQH8HNgsOogkNcQi4J3A16ODlMQZgMH7M/Ac0t0BklS6W4Dd8eA/cF6UFuMx4EfAPcBe\n" +
                    "wKTYOJIU4sfAvsDN0UFK5CmAeNsDJwGbRweRpAF5FHgPaYU/BXEGIN484LukJwnugKdlJLXbRaSr\n" +
                    "/M+MDlI6ZwCaZXvgq8Cu0UEkqWJ3Au8Hvh8dRIkzAM0yj3QP7O3A84GVYuNIUt8WA8cCrwR+F5xF\n" +
                    "I1gAmulK4Fuk518/C5gaG0eSurYE+AnpQT7fJV38rAbxFEDzrQYcDrwLWDs4iySNZxHwP8BngRuC\n" +
                    "s2gMFoB8rAi8mXTl7MaxUSTpSeaTTmF+Hrg1OIs6YAHIzxCwJ3AocBDp7gFJirAYOBf4AXAyPu8k\n" +
                    "KxaAvK0IHEgqAy8CJsfGkVSApcDlwA+BE0gXLytDFoD2mAbsRnrg0CzSmgJe5CmpCjcB5w2P84G7\n" +
                    "YuOoChaA9lqFtL72DsBWw2NLPGUgaXSLgL8ANw6Pa0gH/NsiQ6keFoDyzCCVgQ1IZWD68M9l/57G\n" +
                    "4PeL3YCZA35PKdIc4NcDfs+lwALSefqHRvx8iPQX/Y2kB/MsHnAuBfEhNOWZOzya5BQsACrL5cAh\n" +
                    "0SFUNtedlySpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJ\n" +
                    "kgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIK\n" +
                    "ZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQBkCSpQBYASZIKZAGQJKlAFgBJkgpkAZAkqUAWAEmSCmQB\n" +
                    "kCSpQBYANcGC6ADSgLnPK5wFQE1wR3QAacDc5xXOAqAmmBsdQBow93mFswCoCeZEB5AGzH1e4SwA\n" +
                    "agL/GlJp3OcVzgKgJvDLUKVxn1e4oegAErAS8Eh0CGmApgHzo0OobM4AqAnmA3+LDiENyN/w4K8G\n" +
                    "sACoKU6NDiANiPu6GsECoKbwS1GlcF9XI3gNgJpiEnAXsHp0EKlGfwfWAR6PDiI5A6CmeBw4IzqE\n" +
                    "VLMz8OCvhrAAqElOiQ4g1cx9XI3hKQA1yXTgTtJtgVLbLCBN/z8cHUQCZwDULA8Dx0SHkGryX3jw\n" +
                    "V4M4A6CmWQW4CVg7OohUofuAzYD7o4NIyzgDoKZ5EDgyOoRUsaPx4K+GcQZATTQZ+COwRXQQqQK3\n" +
                    "AlsCj0UHkUZyBkBNtAj4UHQIqSIfx4O/GsgZADXZhcALokNIfbgK2BFYEh1EWp4FQE22HnAZsEF0\n" +
                    "EKkH9wI7AX+NDiI9FU8BqMnmAQfgk9OUn0XAQXjwV4NZANR0VwJvAJZGB5G68E7ggugQ0lgmRgeQ\n" +
                    "OnAdqQDMig4ideArwKeiQ0jjsQAoF78CtgKeER1EGsM5wKF40Z8yYAFQTk4hrRS4a3QQ6Sl8H3gN\n" +
                    "6fy/JKkGh5Huq17qcDRgLAY+gCRpIJ5PenJg9Je/o+zxALAfUoZcB0A5expwGrBddBAV6WZgNuki\n" +
                    "VSk7XgOgnD0AfAd4hLTgygqhaVSKBcDngdcDtwdnkaTirQF8EXiU+GlhRzvH48A3gZlIkhpnI9LV\n" +
                    "2EuIP2A42jN+BjwdqUW8BkBt9QzgdaRztNsGZ1GebiFdY3ICcElsFKl6FgCVYDNSEZgN7AZMio2j\n" +
                    "hloKXAGcSjrwXxMbR6qXBUClWZ10C+EGpHO5M0aMmcCacdE0AA8Ac4E5wz9H/vu3wz+lIvx/KvBH\n" +
                    "m18rWFEAAAAASUVORK5CYII=\", 0, 0, 0)");
        }
    };
}
