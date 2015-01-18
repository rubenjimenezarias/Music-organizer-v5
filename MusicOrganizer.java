import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //Atributo Booleano para saber si esta reproduciendo el reproductor o no.
    private boolean playing;
    
    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("miMusica");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        playing = false;
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if (playing){
            System.out.println("Error ya hay una cancion en reproduccion, pare la que esta sonando para comenzar otra");
        }
        else {
            if(indexValid(index)) {
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                track.morePlayCount();
                playing = true;
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
            }
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if (playing){
            System.out.println("Error ya hay una cancion en reproduccion, pare la que esta sonando para comenzar otra");
        }
        else {
            if(tracks.size() > 0) {
                player.startPlaying(tracks.get(0).getFilename());
                tracks.get(0).morePlayCount();
                playing = true;
            }
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        playing = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * Muestra por pantalla la informacion de los tracks que contienen dicha cadena dne le titulo de la cancion.
     */
    public void findInTitle (String word)
    {
        for(Track track : tracks) {
            if(track.getTitle().contains(word)) {
                System.out.println(track.getDetails());
            }
        }
    }
    /**
     * Para modificar el atributo genero de la clase track
     */
    public void changeGeneroTrack(String newGenero, int index)
    {
        tracks.get(index).changeGenero(newGenero);
    }
    /**
     * Informa por pantalla si el reproductor esta reproduciendo musica o no.
     */
    public void isPlaying()
    {
        if (playing = false){
            System.out.println("No esta sonando ninguna cancion");
        }
        else {
            System.out.println("Esta sonando una cancion");
        }
    }
    /**
     * Muestra por pantalla las canciones de la lista con el metodo iterator
     */
    public void listAllTrackWithIterator()
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track cancion = it.next();
            System.out.println(cancion.getDetails());
        }
    }
    /**
     * borra canciones por el artista que indiquemos.
     */
    public void removeByArtist(String artist)
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track cancion = it.next();
            if (cancion.getArtist().contains(artist)){
                it.remove();
                System.out.println("Usted ha borrado " + cancion.getDetails());
            }
            
        }
    }
    /**
     * borra canciones por el artista que indiquemos.
     */
    public void removeByTitle(String title)
    {
        Iterator<Track> it = tracks.iterator();
        while (it.hasNext()){
            Track cancion = it.next();
            if (cancion.getTitle().contains(title)){
                it.remove();
                System.out.println("Usted ha borrado " + cancion.getDetails());
            }
            
        }
    }
    /**
     * Reproduce una cancion aleatoriamente
     */
    public void playRandom()
    {
        Random aleatorio = new Random();
        int numeroAleatorio = aleatorio.nextInt(tracks.size());
        playTrack(numeroAleatorio);
    }
    /**
     * Metodo que reproduce las canciones aleatoriamente y suma uno al contador de cada cancion y muestra por pantalla lo que esta sonando.
     */
    public void playShuffle() 
    {
        Collections.shuffle(tracks);
        for (Track track : tracks) {
            track.morePlayCount();
            System.out.println("Sonando actualmente: " + track.getDetails());
            player.playSample(track.getFilename());
        }
    }
    /**
     * Metodo que reproduce las canciones aleatoriamente y suma uno al contador de cada cancion y muestra por pantalla lo que esta sonando.
     * Haciendo una copia de la arraylist
     */
    public void playShuffle2() 
    {
        ArrayList<Track> copia = new ArrayList<>(); 
        copia = (ArrayList)tracks.clone();
        
        int cancionesReproducidas = 0;
        
        while (cancionesReproducidas < (tracks.size()))
        {
            //Eligo el numero aleatorio 
            Random aleatorio = new Random();
            int numeroAleatorio = aleatorio.nextInt(tracks.size());
            // sumo el contador de la cancion
            Track track = copia.get(numeroAleatorio);
            track.morePlayCount();
            //Informa de la cancion que suena actualmente
            System.out.println("Sonando actualmente: " + track.getDetails());
            //Reproduce la cancion
            player.playSample(track.getFilename());
            //Elimina la cancion de la lista
            copia.remove(numeroAleatorio);
            // sumamos la cuenta de canciones reproducidas
            cancionesReproducidas ++;
        }
    }
}
