import {CommentDto} from "@/data/CommentDto";

export interface EntryDto {
    authorUsername: string;
    entryContent: string;
    score: number;
    comments: CommentDto[];
}
