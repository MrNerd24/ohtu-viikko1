package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusOnSamaKuin0() {
        Varasto varasto = new Varasto(-1);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaToimii() {
        Varasto varasto = new Varasto(10,5);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaNegatiivinenTilavuus() {
        Varasto varasto = new Varasto(-2,5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaNegatiivinenSaldo() {
        Varasto varasto = new Varasto(10, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAlkusaldollaSaldoVahemmanKuinTilavuus() {
        Varasto varasto = new Varasto(10, 15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void EiVoiLisaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void EiVoiLisaaLiikaaSaldoa() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void EiVoiOttaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-5);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void EiVoiOttaaLiikaaSaldoa() {
        varasto.lisaaVarastoon(8);
        double saatu = varasto.otaVarastosta(10);
        assertEquals(8, saatu, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }

}