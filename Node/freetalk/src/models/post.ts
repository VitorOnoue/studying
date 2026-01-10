import mongoose from 'mongoose';
import { CommentDoc } from './comment.js';

export interface PostDoc extends mongoose.Document {
    title: string,
    content: string,
    comments?: Array<CommentDoc>
}

export interface CreatePostDto {
    title: string,
    content: string,
}

export interface PostModel extends mongoose.Model<PostDoc> {
    build(dto: CreatePostDto): PostDoc;
}

const postSchema = new mongoose.Schema({
    title: {
        type: String,
        require: true
    },
    content: {
        type: String,
        require: true
    },
    comments: [
        {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'comment'
        }
    ]
});

postSchema.statics.build = (createPostDto: CreatePostDto) => new Post(createPostDto);

const Post = mongoose.model<PostDoc, PostModel>('Post', postSchema);

export default Post;