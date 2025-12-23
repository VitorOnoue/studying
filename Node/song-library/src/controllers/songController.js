import song from "../models/Song.js";
import { artist } from "../models/Artist.js";

class SongController {
    static async getAllSongs(req, res) {
        try {
            const songList = await song.find({});
            res.status(200).json(songList);
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to get songs`
            });
        }
    };

    static async getSongById(req, res) {
        try {
            const songById = await song.findById(req.params.id);
            res.status(200).json(songById);
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to get song with id: ${req.params.id}`
            });
        }
    };

    static async createSong(req, res) {
        const newSong = req.body;
        try {
            const artistById = await artist.findById(newSong.artist);
            const songData = { ...newSong, artist: { ...artistById._doc }};
            const createSong = await song.create(songData);
            res.status(201).json({
                message: "song registered successfully",
                song: createSong
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to create song`
            });
        }
    };

    static async updateSongById(req, res) {
        try {
            await song.findByIdAndUpdate(req.params.id, req.body);
            res.status(200).json({
                message: "song updated",
                document: song
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to update song with id: ${req.params.id}`
            });
        }
    };

    static async deleteSongById(req, res) {
        try {
            await song.findByIdAndDelete(req.params.id);
            res.status(200).json({
                message: "song deleted"
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to delete song with id: ${req.params.id}`
            });
        }
    };

    // pretty sure this is not efficient
    static async getSongsByArtist(req, res) {
        const artistName = req.query.artist;
        try {
            const songsByArtist = await song.find({ "artist.name": artistName });
            res.status(200).json(songsByArtist);
        } catch (err) {
            res.status(500).json({ message: `${err.message} - failed to get songs by artist`})
        }
    };
};

export default SongController;