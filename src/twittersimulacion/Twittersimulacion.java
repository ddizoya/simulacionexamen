/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twittersimulacion;

import java.util.List;

import javax.security.auth.login.Configuration;
import javax.swing.JOptionPane;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;

/**
 *
 * @author ddizoya
 */
public class Twittersimulacion {
    public static Twitter twitter;


    public static void main(String[] args) {

        try {
            configurar();
            postearTuit();
            TL();
            buscarPorHashtag();
        } catch (TwitterException ex) {
            System.out.println("Ha habido un error: " + ex.toString());
        } finally {
            System.out.println("Hecho.");
        }

    }

    /**
     * Método para configurar la sesión con twitter.
     * @param Vacío.
     * 
     */
    public static void configurar() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("xFFiKZJDjfXDw2izy0QaHtzj5")
                .setOAuthConsumerSecret("pY58gWpNmC6fWhsKGWFLlb6GBZaII4raWMPJKHABl2Ii2kG3m1")
                .setOAuthAccessToken("3071806397-OijRVSvbNfz0k9IC8EKMWiWWrqbXMUtdWHKE6pp")
                .setOAuthAccessTokenSecret("fNcZiy8pTjx8BMEMCPjzL6Cx788DZ7v27dfkZg71y0V2j");

        twitter = new TwitterFactory(cb.build()).getInstance();
    }

    /**
     * Método que postea un tuit.
     * @throws TwitterException 
     */
    public static void postearTuit() throws TwitterException {
       
        Status status = twitter.updateStatus(Double.toString(Math.random()));
        System.out.println("Publicación posteada con éxito [" + status.getText() + "].");
    }

    /**
     * Método que te permite ver los tuits de tus seguidores.
     * @throws TwitterException 
     */
    public static void TL() throws TwitterException {
       
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Mostrando los tuits de Inicio.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":"
                    + status.getText());
        }
    }

    /**
     * Método que te permite buscar mediante el hashtag que introduzcas.
     * @throws TwitterException 
     */
    public static void buscarPorHashtag() throws TwitterException {
        
        String resp = JOptionPane.showInputDialog("Introduce criterio de búsqueda.");
        Query miquery = new Query("#" + resp);
        QueryResult result = twitter.search(miquery);
        List<Status> tweets = result.getTweets();

        for (Status status :tweets) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }

    }

}
