import {EntryDto} from '@/data/EntryDto';

export interface GetEntriesresponseDto {
    pageNumber: number;
    maxPages: number;
    sizeOfPage: number;
    content: EntryDto[];
}
