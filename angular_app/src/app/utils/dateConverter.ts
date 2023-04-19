export function convertToLocalString(date: Date | string) {
  if (typeof date == 'string') {
    date = new Date(date);
  }
  const mm = date.getMonth() + 1;
  const dd = date.getDate();

  return [(dd > 9 ? '' : '0') + dd,
    (mm > 9 ? '' : '0') + mm,
    date.getFullYear()
  ].join('.');
}
