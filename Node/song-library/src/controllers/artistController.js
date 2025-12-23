import {
    artist
} from "../models/Artist.js";

class ArtistController {
    static async getAllArtists(req, res) {
        try {
            const artistList = await artist.find({});
            res.status(200).json(artistList);
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to get artists`
            });
        }
    };

    static async getArtistById(req, res) {
        try {
            const artistById = await artist.findById(req.params.id);
            res.status(200).json(artistById);
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to get artist with id: ${req.params.id}`
            });
        }
    };

    static async createArtist(req, res) {
        try {
            const newArtist = await artist.create(req.body);
            res.status(201).json({
                message: "artist registered successfully",
                artist: newArtist
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to create artist`
            });
        }
    };

    static async updateArtistById(req, res) {
        try {
            await artist.findByIdAndUpdate(req.params.id, req.body);
            res.status(200).json({
                message: "artist updated",
                document: artist
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to update artist with id: ${req.params.id}`
            });
        }
    };

    static async deleteArtistById(req, res) {
        try {
            await artist.findByIdAndDelete(req.params.id);
            res.status(200).json({
                message: "artist deleted"
            });
        } catch (err) {
            res.status(500).json({
                message: `${err.message} - failed to delete artist with id: ${req.params.id}`
            });
        }
    };
};

export default ArtistController;