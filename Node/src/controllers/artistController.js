import song from "../models/Song.js";

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
        try {
            const newSong = await song.create(req.body);
            res.status(201).json({
                message: "song registered successfully",
                song: newSong
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
                song: song
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
};

export default SongController;