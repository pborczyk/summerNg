import {CommentDto} from '@/data/CommentDto';

export interface EntryDto {
    id: number;
    content: string;
    author: string;
    hashTags: string[];
    upvotes: number;
    timeStamp: Date;
    embedContentType: string;
    embedContent: string;
    comments: CommentDto[];
}
