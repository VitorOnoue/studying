import song from "../models/song.js";

class SongController {
    static async getAllSongs(req, res) {
        const songList = await song.find({});
        res.status(200).json(songList);
    };

    static async createSong (req, res) {
        try {
            const newSong = await song.create(req.body);
            res.status(201).json({ message: "song registered successfully", song: newSong });
        } catch (err) {
            res.status(500).json({ message: `${err.message} - failed to create song`});
        }
        
        
    }
};

export default SongController;