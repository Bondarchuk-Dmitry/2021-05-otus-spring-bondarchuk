
export function authorFullName(author) {
        if (author) return author.firstName + ' ' + author.lastName;
        return '';
}